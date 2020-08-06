package com.bishe.auth.service;

import com.alibaba.fastjson.JSON;
import com.bishe.auth.dao.loginDao;
import com.bishe.framework.client.BsServiceList;
import com.bishe.framework.domain.system.Online;
import com.bishe.framework.domain.ucenter.ext.AuthToken;
import com.bishe.framework.domain.ucenter.response.AuthCode;
import com.bishe.framework.exception.ExceptionCast;
import com.bishe.framework.utils.Detils;
import com.bishe.framework.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    loginDao dao;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    private Calendar timeUtils = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;

    /**
     * 申请用户身份令牌，
     * @param username  用户名
     * @param password  用户密码
     * @param clientId  客户端id
     * @param clientSecret  客户端密码
     * @return  如果有返回则登录成功，否则抛出异常
     */
    public AuthToken login(String username, String password, String clientId, String clientSecret, HttpServletRequest request) {

        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);

        String header = request.getHeader("User-Agent");
        if(authToken == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        //用户身份令牌
        String jti = authToken.getJti();
        //创建新线程写入登录日志

        this.writelog(username, request, jti);

        //存储到Redis中的内容
        String jsonString = JSON.toJSONString(authToken);

        //将令牌存储到Redis
        boolean isSave = saveToken(jti, jsonString, tokenValiditySeconds);
        if(!isSave){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }
        return authToken;
    }

    private void writelog(String username, HttpServletRequest request, String jti) {
        dao.setLoginIp(username, IpUtil.getIp(request));
        Online user = new Online();
        user.setIpaddr(IpUtil.getIp(request));
        user.setLoginLocation(IpUtil.getAddrFromIp(IpUtil.getIp(request)));
        user.setBrowser(Detils.getBrowserName(request));
        user.setOs(Detils.getOsName(request));
        user.setTokenId(jti);
        user.setStatus("1");
        user.setLoginTime(timeUtils.getTime());
        user.setUserName(username);
        dao.writeLog(user);
    }

    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret){
        //请求Spring Security申请令牌
        //从Eureka中获取认证服务的地址
        ServiceInstance choose = loadBalancerClient.choose(BsServiceList.Bs_SERVICE_UCENTER_AUTH);
        URI uri = choose.getUri();
        //令牌的申请地址 40400/auth/oauth/token

        String authUri = uri + "/auth/oauth/token";

        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization", httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });
        //调用Oauth服务来验证用户身份，验证通过则颁发令牌
        ResponseEntity<Map> exchange = restTemplate.exchange(authUri, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if(bodyMap == null ||
                bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null||
                bodyMap.get("jti") == null) {

            if(bodyMap != null && bodyMap.get("error_description") != null){
                String errorDescription = (String) bodyMap.get("error_description");
                if(errorDescription.contains("UserDetailsService returned null")){
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                }else if(errorDescription.contains("坏的凭证")){
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                }
            }
            return null;
        }

        AuthToken authToken = new AuthToken();
        authToken.setAccess_token((String)bodyMap.get("access_token"));
        authToken.setRefresh_token((String)bodyMap.get("refresh_token"));
        authToken.setJti((String)bodyMap.get("jti"));
        return authToken;
    }

    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId+":"+clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }

    /**
     * 将令牌存储到Redis
     * @param jti       用户身份令牌
     * @param content   AuthToken对象的内容
     * @param ttl       过期时间
     * @return          是否存储成功
     */
    private boolean saveToken(String jti, String content, long ttl){

        String key = "user_token:" + jti;
        redisTemplate.boundValueOps(key).set(content, ttl, TimeUnit.SECONDS);
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);

        return expire > 0;
    }

    /**
     * 从Redis取出用户jwt令牌
     * @param jti
     * @return
     */
    public AuthToken getUserToken(String jti){
        String key = "user_token:" + jti;
        String value = redisTemplate.opsForValue().get(key);
        try {
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
            return authToken;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 检查验证码是否正确
     * @param uuid 登录标志
     * @param code 用户输入
     */
    public void checkCode(String uuid, String code){
        String verifyKey = "verifyKey" + uuid;
        Long expire = redisTemplate.getExpire(verifyKey, TimeUnit.SECONDS);
        if(expire <= 0){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_CODE_EXPIRE);
        }
        String redisCode = redisTemplate.opsForValue().get(verifyKey);
        if(redisCode == null || !redisCode.equalsIgnoreCase(code)){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_CODE_ERROR);
        }
    }

    /**
     * 用户退出逻辑
     * @param uid 用户短令牌
     */
    public void logout(String uid) {
        //Redis删除用户令牌
        this.delToken(uid);

        this.updateOnlineStatus(uid);
    }

    private void updateOnlineStatus(String uid) {
        dao.updateOnlineStatus(false ,uid);
    }

    /**
     * 删除Token
     * @param jti 用户短令牌
     * @return
     */
    private void delToken(String jti){
        String key = "user_token:" + jti;
        redisTemplate.delete(key);
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}

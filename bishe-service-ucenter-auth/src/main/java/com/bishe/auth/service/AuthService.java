package com.bishe.auth.service;

import com.alibaba.fastjson.JSON;
import com.bishe.framework.domain.ucenter.ext.AuthToken;
import com.bishe.framework.domain.ucenter.response.AuthCode;
import com.bishe.framework.exception.ExceptionCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;

    //用户身份申请令牌，将令牌存Redis
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
        if(authToken == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }

        //用户身份令牌
        String jti = authToken.getJti();
        //存储到Redis中的内容
        String jsonString = JSON.toJSONString(authToken);

        //将令牌存储到Redis
        boolean isSave = saveToken(jti, jsonString, tokenValiditySeconds);
        if(!isSave){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }
        return authToken;
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

    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret){
        //请求Spring Security申请令牌
        //从Eureka中获取认证服务的地址
        //ServiceInstance choose = loadBalancerClient.choose("xc-server-ucenter-auth");
        //URI uri = choose.getUri();
        //令牌的申请地址 40400/auth/oauth/token

        String authUri = "http://localhost:40400/auth/oauth/token";//uri + "/auth/oauth/token"

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

        ResponseEntity<Map> exchange = restTemplate.exchange(authUri, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if(bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null||
                bodyMap.get("jti") == null){
            return null;
        }

        if(bodyMap.get("error") == "invalid_grant"){
            ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
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
}

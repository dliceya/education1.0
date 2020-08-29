package com.bishe.auth.controller;

import com.bishe.api.auth.AuthControllerApi;
import com.bishe.auth.service.AuthService;
import com.bishe.framework.domain.ucenter.ext.AuthToken;
import com.bishe.framework.domain.ucenter.request.LoginRequest;
import com.bishe.framework.domain.ucenter.response.AuthCode;
import com.bishe.framework.domain.ucenter.response.JwtResult;
import com.bishe.framework.domain.ucenter.response.LoginResult;
import com.bishe.framework.exception.ExceptionCast;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

    @Autowired
    AuthService authService;
    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String ClientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;


    @Override
    @RequestMapping("/userlogin")
    public LoginResult login(@RequestBody LoginRequest loginRequest) {
        //获取请求request实例
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        authService.checkCode(loginRequest.getUuid(),loginRequest.getVerifycode());


        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        //申请令牌
        String loginIp = getCookieByName("ip");
        AuthToken authToken = authService.login(username, password, clientId, ClientSecret, request, loginIp);

        String jti = authToken.getJti();
        this.saveCookie(jti);

        LoginResult loginResult = new LoginResult(CommonCode.SUCCESS, jti);
        return loginResult;
    }

    @Override
    @GetMapping("/logout")
    public ResponseResult logout(@RequestParam String token) {
        String uid = "";
        if(StringUtils.isEmpty(token)) {
            uid = getCookieByName("uid");
            this.clearCookie(uid);
        }else {
            uid = token;
        }
        //删除Redis中的Token

        authService.logout(uid);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @GetMapping("/userjwt")
    public JwtResult userJwt() {
        //取出Cookie中的用户身份令牌
        String uid = getCookieByName("uid");
        if(uid == null){
            return new JwtResult(CommonCode.FAIL, null);
        }

        //拿身份令牌在Redis中查询jwt令牌
        AuthToken authToken = authService.getUserToken(uid);
        if(authToken != null){
            String jwt = authToken.getAccess_token();
            return new JwtResult(CommonCode.SUCCESS, jwt);
        }

        //返回jwt给用户
        return null;
    }


    //取出Cookie中的身份令牌
    private String getCookieByName(String name){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Map<String, String> map = CookieUtil.readCookie(request, name);
        if(map != null && map.get(name) != null){
            return map.get(name);
        }
        return null;
    }


    /**
     * 用uid为key来存储用户令牌到Cookie
     * @param jti   用户令牌
     */
    private void saveCookie(String jti){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",jti,cookieMaxAge,false);
    }

    private void clearCookie(String jti){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",jti,0,false);
    }
}

package com.bishe.auth.controller;

import com.bishe.api.auth.AuthControllerApi;
import com.bishe.auth.service.AuthService;
import com.bishe.framework.domain.ucenter.ext.AuthToken;
import com.bishe.framework.domain.ucenter.request.LoginRequest;
import com.bishe.framework.domain.ucenter.response.AuthCode;
import com.bishe.framework.domain.ucenter.response.LoginResult;
import com.bishe.framework.exception.ExceptionCast;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

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
    public LoginResult login(LoginRequest loginRequest) {

        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }

        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        //申请令牌
        AuthToken authToken = authService.login(username, password, clientId, ClientSecret);

        String jti = authToken.getJti();
        this.saveCookie(jti);

        return new LoginResult(CommonCode.SUCCESS, jti);
    }

    @Override
    public ResponseResult logout() {
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
}

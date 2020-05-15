package com.bishe.govern.gateway.service;

import com.bishe.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    StringRedisTemplate redisTemplate;

    //从请求头中获取用户令牌
    public String getJwtFromHeader(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        if(StringUtils.isEmpty(authorization)){
            return null;
        }
        if(authorization.startsWith("Bearer ")){
            return null;
        }

        String jwt = authorization.substring(7);
        return jwt;
    }

    //从Cookie中获取用户短令牌
    public String getJtiFromCookie(HttpServletRequest request){
        Map<String, String> cookieMap = CookieUtil.readCookie(request,"uid");
        String jti = cookieMap.get("uid");
        if(StringUtils.isEmpty(jti)){
            return null;
        }
        return jti;
    }

    //查询用户令牌有效期
    public Long getExpire(String jti){
        String key = "user_token:" + jti;
        Long expire = redisTemplate.getExpire("key", TimeUnit.SECONDS);
        return expire;
    }
}

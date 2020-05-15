package com.bishe.govern.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.govern.gateway.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤请求，验证Header中的Jwt和Cookie中的jti是否存在，并验证是否有效，有效的请求则放行
 */
@Component
public class LoginFilter extends ZuulFilter {

    @Autowired
    AuthService authService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        //从Cookie中取令牌
        String jti = authService.getJtiFromCookie(request);
        if(StringUtils.isEmpty(jti)){
            //拒绝访问
            access_denied(currentContext, response);
        }

        //从Header中取JWT
        String jwt = authService.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwt)){
            //拒绝访问
            access_denied(currentContext, response);
        }

        //验证令牌有效期
        Long expire = authService.getExpire(jti);
        if(expire < 0){
            //拒绝访问
            access_denied(currentContext, response);
        }
        return null;
    }

    //拒绝访问，设置返回信息
    private void access_denied(RequestContext requestContext,HttpServletResponse response){
        //拒接访问
        requestContext.setSendZuulResponse(false);
        //设置响应代码
        requestContext.setResponseStatusCode(200);
        //构建响应信息
        ResponseResult responseResult = new ResponseResult(CommonCode.UNAUTHENTICATED);
        //转化为Json数据
        String res = JSON.toJSONString(responseResult);
        requestContext.setResponseBody(res);

        response.setContentType("application/json;charset=utf-8");
    }
}

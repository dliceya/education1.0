package com.bishe.api.auth;

import com.bishe.framework.domain.ucenter.request.LoginRequest;
import com.bishe.framework.domain.ucenter.response.JwtResult;
import com.bishe.framework.domain.ucenter.response.LoginResult;
import com.bishe.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户认证", description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public LoginResult login(LoginRequest loginRequest0);

    @ApiOperation("退出")
    public ResponseResult logout();

    @ApiOperation("查询用户jwt令牌")
    public JwtResult userJwt();
}

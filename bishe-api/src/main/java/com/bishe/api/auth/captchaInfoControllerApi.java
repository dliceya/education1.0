package com.bishe.api.auth;

import com.bishe.framework.domain.ucenter.response.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "用户登录信息获取")
public interface captchaInfoControllerApi {

    @ApiOperation("获取图片验证码")
    public AjaxResult getCode(HttpServletResponse response) throws IOException;
}

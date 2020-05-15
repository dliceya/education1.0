package com.bishe.framework.domain.ucenter.response;

import com.bishe.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@ToString
public enum AuthCode implements ResultCode {
    AUTH_USERNAME_NONE(false,23001,"请输入账号！"),
    AUTH_PASSWORD_NONE(false,23002,"请输入密码！"),
    AUTH_VERIFYCODE_NONE(false,23003,"请输入验证码！"),
    AUTH_ACCOUNT_NOTEXISTS(false,23004,"账号不存在！"),
    AUTH_CREDENTIAL_ERROR(false,23005,"账号或密码错误！"),
    AUTH_LOGIN_ERROR(false,23006,"登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(false,23007,"申请令牌失败！ 请与系统管理员联系：admin.dlice@bishe.com" ),
    AUTH_LOGIN_TOKEN_SAVEFAIL(false,23008,"存储令牌失败！ 请与系统管理员联系：admin.dlice@bishe.com"),
    AUTH_LOGOUT_FAIL(false,23008,"退出失败！"),
    REDIS_ERROR(false,66666,"Redis通信失败，请携带此信息联系网站管理员！"),
    AUTH_LOGIN_CODE_EXPIRE(false,23010,"验证码已过期！"),
    AUTH_LOGIN_CODE_ERROR(false, 23009, "验证码错误！");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private AuthCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}

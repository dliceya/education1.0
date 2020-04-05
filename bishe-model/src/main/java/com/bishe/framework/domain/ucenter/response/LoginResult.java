package com.bishe.framework.domain.ucenter.response;

import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;

public class LoginResult extends ResponseResult {

    private String jti;

    public LoginResult(ResultCode resultCode, String jti){
        super(resultCode);
        this.jti = jti;
    }
}

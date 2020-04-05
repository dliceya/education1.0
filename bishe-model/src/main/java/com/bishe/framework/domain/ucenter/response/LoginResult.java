package com.bishe.framework.domain.ucenter.response;

import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;

public class LoginResult extends ResponseResult {

    private String token;

    public LoginResult(ResultCode resultCode, String token){
        super(resultCode);
        this.token = token;
    }
}

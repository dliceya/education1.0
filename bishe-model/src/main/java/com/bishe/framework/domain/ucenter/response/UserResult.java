package com.bishe.framework.domain.ucenter.response;

import com.bishe.framework.domain.ucenter.BsUser;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResult extends ResponseResult {

    BsUser bsUser;
    public UserResult(ResultCode resultCode, BsUser bsUser){
        super(resultCode);
        this.bsUser = bsUser;
    }

}

package com.bishe.framework.domain.user.response;

import com.bishe.framework.domain.user.User;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResult extends ResponseResult {

    User user;
    public UserResult(ResultCode resultCode, User user){
        super(resultCode);
        this.user = user;
    }

}

package com.bishe.framework.domain.ucenter.response;

import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;

/**
 * 验证码
 */
@Data
public class AjaxResult extends ResponseResult {
    private String uuid;
    private String img;

    public AjaxResult(ResultCode resultCode, String uuid, String img){
        super(resultCode);
        this.uuid = uuid;
        this.img = img;
    }

}


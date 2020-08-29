package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.system.Online;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class OnlineResponse extends ResponseResult {
    private List<Online> onlineList;

    private int total;

    public OnlineResponse(ResultCode resultCode, List<Online> onlineList){
        super(resultCode);
        this.onlineList = onlineList;
    }
}

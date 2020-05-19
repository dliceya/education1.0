package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.system.Role;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryRoleResult extends ResponseResult {

    private List<Role> roleList;
    public int total;

    public QueryRoleResult(ResultCode resultCode, List<Role> roleList){
        super(resultCode);
        this.roleList = roleList;
    }
}

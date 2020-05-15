package com.bishe.framework.domain.department.response;

import com.bishe.framework.domain.department.Department;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class QueryDeptTreeResponse extends ResponseResult {

    private List<Department> list;

    public QueryDeptTreeResponse(ResultCode resultCode, List<Department> list){
        super(resultCode);
        this.list = list;
    }
}

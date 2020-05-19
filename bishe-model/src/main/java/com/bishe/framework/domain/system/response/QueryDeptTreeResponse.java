package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.department.Department;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDeptTreeResponse extends ResponseResult {

    private List<Department> deptTree;

    public QueryDeptTreeResponse(ResultCode resultCode, List<Department> deptTree){
        super(resultCode);
        this.deptTree = deptTree;
    }
}

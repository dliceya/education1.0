package com.bishe.api.system;

import com.bishe.framework.domain.system.response.QueryDeptTreeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "系统资源管理", description = "系统资源接口")
public interface SystemControllerApi {

    @ApiOperation("获取部门列表")
    QueryDeptTreeResponse getDeptTree();
}

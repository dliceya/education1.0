package com.bishe.api.ucenter;

import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import com.bishe.framework.domain.ucenter.response.QueryPageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户中心", description = "用户中心管路")
public interface UcenterControllerApi {

    @ApiOperation("根据用户账号查询用户信息")
    public BsUserExt getUserExt(String username);

    @ApiOperation("获取用户列表")
    public QueryPageResponse queryUser(QueryPageRequest pageRequest);
}

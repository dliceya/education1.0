package com.bishe.api.ucenter;

import com.bishe.framework.domain.ucenter.BsUser;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import com.bishe.framework.domain.ucenter.response.UserResult;
import com.bishe.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="用户管理接口", description = "用户管理界面接口，提供用户的增删改查等")
public interface UserControllerApi {

    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findAll(int page, int size, QueryPageRequest queryPageRequest);

    //添加用户
    @ApiOperation("添加用户")
    public UserResult addUser(BsUser bsUser);

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ucenter",value = "登录信息",required=true,paramType="Json"
             ,dataType="User{userName, Password}")
    })
    public UserResult login(BsUser bsUser);

}

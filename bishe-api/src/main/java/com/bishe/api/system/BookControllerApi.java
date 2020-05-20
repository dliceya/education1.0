package com.bishe.api.system;

import com.bishe.framework.domain.system.response.QueryBookResponse;
import com.bishe.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "履历本管理")
public interface BookControllerApi {

    @ApiOperation("根据履历本id获取履历本详细信息")
    public QueryBookResponse getBook(String uid);

    public ResponseResult addBook(String name, String createBy);

    public ResponseResult delBook(String bid);

    public ResponseResult updateBook(String bid, String name);
}

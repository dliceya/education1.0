package com.bishe.system.service.impl;

import com.bishe.framework.domain.system.response.QueryBookResponse;
import com.bishe.framework.model.response.ResponseResult;

public interface IBookService {

    //获取当前用户的履历本
    QueryBookResponse getBookById(String uid);

    //添加履历本
    ResponseResult addBook(String name, String createBy);

    //删除履历本
    ResponseResult delBook(String bid);

    //改名
    ResponseResult updateBook(String bid, String name);
}

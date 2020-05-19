package com.bishe.system.service.impl;

import com.bishe.framework.domain.record.Record;
import com.bishe.framework.domain.system.request.QueryRecordRequest;
import com.bishe.framework.domain.system.response.QueryRecordResponse;
import com.bishe.framework.model.response.ResponseResult;

import java.util.List;

public interface IRecordService {

    //分页查询履历信息
    QueryRecordResponse getList(QueryRecordRequest request);

    //添加履历记录
    ResponseResult addRecord(Record record);

    //删除履历记录
    ResponseResult delRecord(List<String> rids);

    //结束履历状态
    ResponseResult endStatus(String rid);
}

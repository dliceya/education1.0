package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.record.Record;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class QueryRecordResponse extends ResponseResult {
    public List<Record> recordList;

    public int total;

    public QueryRecordResponse(ResultCode resultCode, List<Record> recordList){
        super(resultCode);
        this.recordList = recordList;
    }
}

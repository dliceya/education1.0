package com.bishe.framework.domain.system.request;

import lombok.Data;

import java.util.Date;

@Data
public class QueryRecordRequest {
    private String bid;
    private String ename;
    private String type;
    private Date beginTime;
    private Date endTime;
    private int pageNum;
    private int pageSize;
}

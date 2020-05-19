package com.bishe.framework.domain.system.request;

import lombok.Data;

import java.util.Date;

@Data
public class MenuListRequest {
    private String name;
    private String component;
    private String status;
    private Date beginTime;
    private Date endTime;
    private int pageNum;
    private int pageSize;
}

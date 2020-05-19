package com.bishe.framework.domain.system.request;

import lombok.Data;

import java.util.Date;

@Data
public class EquipListRequest {
    private String name;
    private String eNum;
    private String type;
    private Date beginTime;
    private Date endTime;
    private int pageNum;
    private int pageSize;
}

package com.bishe.framework.domain.system.request;

import lombok.Data;

import java.util.Date;

@Data
public class QueryRoleRequest {
    private String roleCode;
    private String roleName;
    private String status;

    private Date beginTime;

    private Date endTime;

    private int pageNum;
    private int pageSize;
}

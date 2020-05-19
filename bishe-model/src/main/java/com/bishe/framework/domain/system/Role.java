package com.bishe.framework.domain.system;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Role {
    private String rid;

    private String roleName;

    private String roleCode;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String status;
}

package com.bishe.framework.domain.system;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Permission {

    //权限id
    private String pid;

    //父级权限id
    private String parent;

    //权限名
    private String permissionName;

    //更新时间
    private Date updateTime;

    //创建时间
    private Date createTime;

    //说明
    private String remark;

    //子级权限列表
    private List<Permission> child;
}

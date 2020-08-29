package com.bishe.framework.domain.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //创建时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //说明
    private String remark;

    //子级权限列表
    private List<Permission> child;
}

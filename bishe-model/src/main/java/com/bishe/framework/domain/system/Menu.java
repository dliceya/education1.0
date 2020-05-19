package com.bishe.framework.domain.system;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Menu {
    //菜单id
    private String mid;

    //菜单名
    private String name;

    //父菜单id
    private String parent;

    //子菜单列表
    private List<Menu> child;

    //排序字段
    private int orderNum;

    //组件名称
    private String component;

    //图标
    private String icon;

    //状态
    private String status;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String remark;
}

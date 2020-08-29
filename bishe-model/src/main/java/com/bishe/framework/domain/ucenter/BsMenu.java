package com.bishe.framework.domain.ucenter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BsMenu {

    //功能id
    private String id;

    //功能名称代码： bishe_sys_manager
    private String code;

    //功能名称系统管理
    private String menuName;

    //Url
    private String url;

    //是否启用权限管理
    private String isMenu;

    //功能等级
    private Integer level;

    //排序自段
    private Integer sort;

    //状态
    private String status;

    //图标
    private String icon;

    //创建时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //更新时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
package com.bishe.framework.domain.book;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class book {
    //id
    private String bid;

    //履历本别名
    private String bName;

    //所属部门id
    private String pid;

    //履历记录Id
    private List<?> rId;

    //创建者id
    private String createUserId;

    //创建时间
    private Long createTime;

    //是否启用，0表示停用
    private String Active;

    //履历本权限等级
    private String power;
}

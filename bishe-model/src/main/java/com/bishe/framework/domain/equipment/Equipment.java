package com.bishe.framework.domain.equipment;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Equipment {
    //装备Id
    private String eid;

    //装备编号
    private String eNum;

    //装备名称
    private String name;

    //装备类型
    private String type;

    //生产部门id
    private String byId;

    //交付日期
    private Date deliverTime;

    //生产日期
    private Date createTime;

    //部门id
    private int life;
}

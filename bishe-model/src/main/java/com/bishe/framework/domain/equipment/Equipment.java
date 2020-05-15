package com.bishe.framework.domain.equipment;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Equipment {
    //装备Id
    private String eid;

    //装备名称
    private String name;

    //装备编号
    private String eNum;

    //装备类型
    private String type;

    //交付日期
    private String deliverTime;

    //状态模型
    private String statusModel;
}

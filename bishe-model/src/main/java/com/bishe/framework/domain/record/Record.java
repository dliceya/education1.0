package com.bishe.framework.domain.record;

import lombok.Data;

import java.util.Date;

@Data
public class Record {
    //履历id
    private String rid;

    //所属履历本
    private String bid;

    //创建时间
    private Date createTime;

    //装备id
    private String eid;

    /**
     * 1：使用
     * 2：封存
     * 3：维修
     * 4：故障
     * 5：启封
     */
    private String type;

    //持续时间
    private int time;

    //使用者
    private String user;

    //修改标记
    private Date updateTime;

    //开始日期
    private Date beginTime;

    //结束日期
    private Date endTime;


}

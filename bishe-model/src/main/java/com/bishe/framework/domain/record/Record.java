package com.bishe.framework.domain.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Record {
    //履历id
    private String rid;

    //所属履历本
    private String bid;

    //创建时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //开始日期
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  beginTime;

    //结束日期
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  endTime;


}

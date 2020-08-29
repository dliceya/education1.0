package com.bishe.framework.domain.book;

import com.bishe.framework.domain.record.Record;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class Book {
    //id
    private String bid;

    //履历本别名
    private String bookName;

    //履历记录Id
    private List<Record> recordId;

    //创建者id
    private String createBy;

    //创建时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //更新时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

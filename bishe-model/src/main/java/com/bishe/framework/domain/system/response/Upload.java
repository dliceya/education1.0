package com.bishe.framework.domain.system.response;

import lombok.Data;

import java.util.List;

@Data
public class Upload {
    //日期列表
    List<String> date;
    //持续时间列表
    List<String> time;
}

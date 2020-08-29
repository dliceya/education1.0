package com.bishe.framework.domain.ucenter.request;

import lombok.Data;

//接收用户查询的查询条件
@Data
public class QueryPageRequest {
    Integer pageNum;
    Integer pageSize;
    String username;
    String phone;
    Integer status;
    String beginTime;
    String endTime;
    String pid;
}

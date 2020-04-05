package com.bishe.framework.domain.user.request;

import lombok.Data;

//接收用户查询的查询条件
@Data
public class QueryPageRequest {

    String userName;

    String department;
}

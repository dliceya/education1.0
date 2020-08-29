package com.bishe.framework.domain.system.request;

import lombok.Data;

@Data
public class OnlineRequest {
    private String loginLocation;

    private String userName;

    private String status;

    private int pageNum;

    private int pageSize;
}

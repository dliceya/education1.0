package com.bishe.framework.domain.system;

import lombok.Data;

import java.util.Date;

@Data
public class Online {
    private String tokenId;
    private String userName;
    private String deptName;
    private String ipaddr;
    private String loginLocation;
    private String browser;
    private String os;
    private Date loginTime;
    private String status;
}

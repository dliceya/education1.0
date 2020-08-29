package com.bishe.framework.domain.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Online {
    private String tokenId;

    private String userName;

    private String deptName;

    private String ipaddr;

    private String loginLocation;

    private String browser;

    private String os;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    private String status;
}

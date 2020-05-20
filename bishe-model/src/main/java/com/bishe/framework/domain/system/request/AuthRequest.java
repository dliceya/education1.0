package com.bishe.framework.domain.system.request;

import lombok.Data;

import java.util.List;

@Data
public class AuthRequest {
    private String rid;

    private List<String> permissionList;
}

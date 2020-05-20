package com.bishe.system.service.impl;

import com.bishe.framework.domain.system.Permission;
import com.bishe.framework.domain.system.request.AuthRequest;

import java.util.List;

public interface IPermissionService {

    //获取系统权限列表
    List<Permission> getPermissionList();

    //系统角色授权
    boolean authUser(AuthRequest authRequest);

    //查询当前角色的授权信息
    List<String> getCurrentPermission(String rid);
}

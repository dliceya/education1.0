package com.bishe.system.controller;

import com.bishe.framework.domain.system.Permission;
import com.bishe.framework.domain.system.request.AuthRequest;
import com.bishe.system.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    //获取权限列表
    @PreAuthorize("hasAuthority('system:auth:list')")
    @RequestMapping("/list")
    public List<Permission> getPermissionList(){
        return service.getPermissionList();
    }


    //角色授权
    @PreAuthorize("hasAuthority('system:auth:auth')")
    @RequestMapping("/auth")
    public boolean authUser(@RequestBody AuthRequest authRequest){
        return service.authUser(authRequest);
    }

    //查询当前角色的授权信息
    @PreAuthorize("hasAuthority('system:auth:current')")
    @RequestMapping("current")
    public List<String> current(@RequestParam String rid){
        return service.getCurrentPermission(rid);
    }
}

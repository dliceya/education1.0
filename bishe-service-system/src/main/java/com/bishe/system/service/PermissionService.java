package com.bishe.system.service;

import com.bishe.framework.domain.system.Permission;
import com.bishe.framework.domain.system.request.AuthRequest;
import com.bishe.system.dao.PermissionDao;
import com.bishe.system.service.impl.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    private final PermissionDao perDao;

    public PermissionService(PermissionDao perDao) {
        this.perDao = perDao;
    }

    @Override
    public List<Permission> getPermissionList() {

        //获取祖级权限列表
        List<Permission> permissionList = perDao.getPermissionListById("0");

        if(permissionList != null) {
            //获取子级权限列表
            permissionList.forEach(items -> items.setChild(perDao.getPermissionListById(items.getPid())));
        }

        return permissionList;
    }

    @Override
    public boolean authUser(AuthRequest authRequest) {

        String rid = authRequest.getRid();
        List<String> pids = authRequest.getPermissionList();
        List<String> menuChild = new ArrayList<>();

        //检查每一个权限id，若该权限是一个菜单级别的权限，就查出所有该权限的子权限添加到权限列表后面
        pids.forEach(pid -> {
            if(perDao.isMenu(pid) > 0){
                menuChild.addAll(perDao.getMenuChild(pid));
            }
        });
        pids.addAll(menuChild);
        //清除之前的授权
        perDao.delAuth(rid);

        boolean flag = true;

        //从新写入前端接收的用户权限
        for(String pid: pids){
            if(!perDao.auth(rid, pid)){
                flag = false;
            }
        }

        return flag;
    }

    @Override
    public List<String> getCurrentPermission(String rid) {
        return perDao.getCurrentPermission(rid);
    }
}

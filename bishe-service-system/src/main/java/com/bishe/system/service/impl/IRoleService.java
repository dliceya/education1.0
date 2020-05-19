package com.bishe.system.service.impl;

import com.bishe.framework.domain.system.Role;
import com.bishe.framework.domain.system.request.QueryRoleRequest;
import com.bishe.framework.domain.system.response.QueryRoleResult;
import com.bishe.framework.model.response.ResponseResult;

import java.util.List;

public interface IRoleService {
    //获取系统角色列表
    QueryRoleResult getRoleList(QueryRoleRequest request);

    //添加系统角色列表
    ResponseResult addRole(Role role);

    //删除系统角色列表
    boolean delRole(List<String> rids);

    //修改系统角色列表
    ResponseResult updateRole(Role role);

    //根据id获取系统角色
    Role getRoleById(String rid);

    //修改角色状态
    boolean changeRoleStatus(String rid, String status);

    //查询角色下拉列表
    QueryRoleResult getRoleSelect();

}

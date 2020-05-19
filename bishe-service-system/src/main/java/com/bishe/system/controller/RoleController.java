package com.bishe.system.controller;

import com.bishe.framework.domain.system.Role;
import com.bishe.framework.domain.system.request.QueryRoleRequest;
import com.bishe.framework.domain.system.response.QueryRoleResult;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //获取角色列表 分页查询全部
    @RequestMapping("/rolelist")
    public QueryRoleResult getRole(@RequestBody QueryRoleRequest request){

        return roleService.getRoleList(request);
    }

    @RequestMapping("roleSelect")
    public QueryRoleResult getRoleSelect(){
        return roleService.getRoleSelect();
    }

    //通过获取角色
    @GetMapping("role")
    public Role getRoleById(@RequestParam("rid")String rid){
        return roleService.getRoleById(rid);
    }

    //修改角色状态
    @GetMapping("changeRoleStatus")
    public boolean changeRoleStatus(@RequestParam("rid")String rid, @RequestParam("status")String status){
        return roleService.changeRoleStatus(rid, status);
    }

    //添加角色
    @RequestMapping("/addrole")
    public ResponseResult addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    //修改角色
    @RequestMapping("/updaterole")
    public ResponseResult updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    //删除角色
    @RequestMapping("/deluser")
    public boolean delUser(@RequestBody List<String> rids){
        return roleService.delRole(rids);
    }
}

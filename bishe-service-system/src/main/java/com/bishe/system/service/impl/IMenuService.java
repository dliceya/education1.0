package com.bishe.system.service.impl;

import com.bishe.framework.domain.system.Menu;
import com.bishe.framework.domain.system.request.MenuListRequest;
import com.bishe.framework.domain.system.response.QueryMenuResult;
import com.bishe.framework.model.response.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMenuService {
    //获取系统菜单列表（包含child）
    QueryMenuResult getMenuList();

    //获取系统祖级菜单
    List<Menu> getMenuParent();

    //获取系统菜单
    QueryMenuResult getList(MenuListRequest request);

    //添加菜单
    ResponseResult addMenu(Menu menu);

    //删除菜单
    ResponseResult delMenu(List<String> mids);

    //更新菜单
    ResponseResult updateMenu(Menu menu);

    //根据id查询菜单
    Menu getMenuByid(String mid);

    //修改角色状态
    boolean changeRoleStatus(String mid, String status);
}

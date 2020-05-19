package com.bishe.system.controller;

import com.bishe.framework.domain.system.Menu;
import com.bishe.framework.domain.system.request.MenuListRequest;
import com.bishe.framework.domain.system.response.QueryMenuResult;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.system.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/parentmenu")
    public List<Menu> getParent(){
        return menuService.getMenuParent();
    }

    //多级列表
    @GetMapping("/menuList")
    public QueryMenuResult getMenu(){
        return menuService.getMenuList();
    }

    //系统菜单列表
    @RequestMapping("/list")
    public QueryMenuResult getList(@RequestBody MenuListRequest request){
        return menuService.getList(request);
    }

    //根据id查询菜单
    @GetMapping("/getmenu")
    public Menu getMenuByid(@RequestParam String mid){
        return menuService.getMenuByid(mid);
    }

    //修改角色状态
    @GetMapping("changemenustatus")
    public boolean changeMenuStatus(@RequestParam("mid")String mid, @RequestParam("status")String status){
        return menuService.changeRoleStatus(mid, status);
    }

    //新增菜单
    @RequestMapping("/addmenu")
    public ResponseResult addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    //删除菜单
    @RequestMapping("delmenu")
    public ResponseResult delMenu(@RequestBody List<String> mids){
        return menuService.delMenu(mids);
    }

    //更新菜单
    @RequestMapping("updatemenu")
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }
}

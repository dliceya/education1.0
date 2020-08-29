package com.bishe.system.service;

import com.bishe.framework.domain.system.Menu;
import com.bishe.framework.domain.system.request.MenuListRequest;
import com.bishe.framework.domain.system.response.QueryMenuResult;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.IdUtils;
import com.bishe.system.dao.IMenuDao;
import com.bishe.system.service.impl.IMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MenuService implements IMenuService {

    private final IMenuDao iMenuDao;

    public MenuService(IMenuDao iMenuDao) {
        this.iMenuDao = iMenuDao;
    }

    @Override
    public QueryMenuResult getMenuList() {
        QueryMenuResult result;
        //获取祖级菜单
        List<Menu> menuList = iMenuDao.getMenuListByMid("0");

        if(menuList != null) {
            //获取子级菜单
            menuList.forEach(items -> items.setChild(iMenuDao.getMenuListByMid(items.getMid())));
            result = new QueryMenuResult(CommonCode.SUCCESS, menuList);
        }else result = new QueryMenuResult(CommonCode.FAIL, null);

        result.setTotal(iMenuDao.getMenuListTotal());

        return result;
    }

    @Override
    public List<Menu> getMenuParent() {
        return iMenuDao.getMenuListByMid("0");
    }

    @Override
    public QueryMenuResult getList(MenuListRequest request) {
        QueryMenuResult result;
        request.setPageNum((request.getPageNum() - 1) * request.getPageSize());

        List<Menu> menuList = iMenuDao.getList(request);

        if(menuList != null){
            menuList.forEach(item -> item.setParent(iMenuDao.getNameByMid(item.getParent())));
            result = new QueryMenuResult(CommonCode.SUCCESS, menuList);
            result.setTotal(iMenuDao.getTotal());
        } else {
            result = new QueryMenuResult(CommonCode.FAIL, null);
            result.setTotal(0);
        }

        return result;
    }

    @Override
    public ResponseResult addMenu(Menu menu) {
        ResponseResult result;
        if(StringUtils.isEmpty(menu.getParent())){
            menu.setParent("0");
        }
        menu.setCreateBy("root");
        menu.setCreateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        menu.setUpdateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        menu.setMid(IdUtils.simpleUUID());

        if(iMenuDao.addMenu(menu) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);

        return result;
    }

    @Override
    public ResponseResult delMenu(List<String> mids) {

        ResponseResult result;
        int count = 0;
        for (String mid : mids) {
            count += iMenuDao.delMenu(mid);
        }
        if(count > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);
        return result;
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        ResponseResult result;

        menu.setUpdateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        if(iMenuDao.updateMenu(menu) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);
        return result;
    }

    @Override
    public Menu getMenuByid(String mid) {
        return iMenuDao.getMenuByid(mid);
    }

    @Override
    public boolean changeRoleStatus(String mid, String status) {
        return iMenuDao.changeStatus(mid, status);
    }
}

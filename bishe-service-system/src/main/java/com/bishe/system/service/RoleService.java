package com.bishe.system.service;

import com.bishe.framework.domain.system.Role;
import com.bishe.framework.domain.system.request.QueryRoleRequest;
import com.bishe.framework.domain.system.response.QueryRoleResult;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.IdUtils;
import com.bishe.system.dao.RoleDao;
import com.bishe.system.service.impl.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class RoleService implements IRoleService {
    
    private final RoleDao roleDao;

    private Calendar timeUtils = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public QueryRoleResult getRoleList(QueryRoleRequest pageRequest) {
        pageRequest.setPageNum((pageRequest.getPageNum() - 1) * pageRequest.getPageSize());

        List<Role> roleList = roleDao.getRoleList(pageRequest);

        QueryRoleResult result = new QueryRoleResult(CommonCode.SUCCESS, roleList);

        result.setTotal(roleDao.getTotal(pageRequest));

        return result;
    }

    @Override
    public ResponseResult addRole(Role role) {
        ResponseResult result;
        role.setCreateTime(timeUtils.getTime());
        role.setUpdateTime(timeUtils.getTime());
        role.setRid(IdUtils.simpleUUID());


        if(roleDao.addRole(role) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else {
            result = new ResponseResult(CommonCode.FAIL);
        }

        return result;
    }

    @Override
    public boolean delRole(List<String> rids) {

        int count = 0;

        for (String rid : rids) {
            count += roleDao.delRole(rid);
        }
        return count >0;
    }

    @Override
    public ResponseResult updateRole(Role role) {
        role.setUpdateTime(timeUtils.getTime());
        ResponseResult result;

        role.setCreateTime(timeUtils.getTime());
        role.setUpdateTime(timeUtils.getTime());
        if(roleDao.updateRole(role) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else {
            result = new ResponseResult(CommonCode.FAIL);
        }
        return result;
    }

    @Override
    public Role getRoleById(String rid) {
        return roleDao.getRoleById(rid);
    }

    @Override
    public boolean changeRoleStatus(String rid, String status) {
        Role role = roleDao.getRoleById(rid);
        role.setStatus(status);
        return roleDao.updateRole(role) > 0;
    }

    @Override
    public QueryRoleResult getRoleSelect() {

        List<Role> roleList = roleDao.getRoleSelect();
        QueryRoleResult result;
        if(roleList != null){
            result = new QueryRoleResult(CommonCode.SUCCESS, roleList);
        }else result = new QueryRoleResult(CommonCode.FAIL, null);

        return result;
    }
}

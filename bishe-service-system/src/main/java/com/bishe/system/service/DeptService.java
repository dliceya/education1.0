package com.bishe.system.service;

import com.bishe.framework.domain.department.Department;
import com.bishe.framework.domain.system.response.QueryDeptTreeResponse;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.system.dao.IDeptDao;
import com.bishe.system.service.impl.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService implements IDeptService {

    private final IDeptDao iDeptDao;

    @Autowired
    public DeptService(IDeptDao iDeptDao) {
        this.iDeptDao = iDeptDao;
    }

    public QueryDeptTreeResponse getDeptTree() {

        QueryDeptTreeResponse response;

        List<Department> oneDeptList = new ArrayList<>();

        //查询所有的一级部门
        oneDeptList = iDeptDao.getDeptListByParent("0");

        //查询所有二级部门
        oneDeptList.forEach(item -> {
            item.setChild(iDeptDao.getDeptListByParent(item.getPid()));
        });

        //查询三级部门
        oneDeptList.forEach(items -> {
            items.getChild().forEach(item -> {
                item.setChild(iDeptDao.getDeptListByParent(item.getPid()));
            });
        });

        if(oneDeptList != null){
            response = new QueryDeptTreeResponse(CommonCode.SUCCESS, oneDeptList);
        } else {
            response = new QueryDeptTreeResponse(CommonCode.FAIL, null);
        }
        return response;
    }






}

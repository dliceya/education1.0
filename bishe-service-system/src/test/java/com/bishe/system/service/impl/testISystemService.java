package com.bishe.system.service.impl;

import com.bishe.framework.domain.system.Permission;
import com.bishe.framework.domain.system.response.QueryDeptTreeResponse;
import com.bishe.system.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testISystemService {

    @Autowired
    IDeptService service;

    @Autowired
    PermissionService permissionService;

    @Test
    public void testgetDeptTree(){
        QueryDeptTreeResponse response = service.getDeptTree();
        System.out.println(response);
    }

    @Test
    public void testPermissionService(){
        List<Permission> permissionList = permissionService.getPermissionList();
        System.out.println(permissionList);
    }
}

package com.bishe.system.controller;

import com.bishe.api.system.SystemControllerApi;
import com.bishe.framework.domain.system.response.QueryDeptTreeResponse;
import com.bishe.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/dept")
public class DeptController implements SystemControllerApi {

    @Autowired
    DeptService systemService;

    @RequestMapping("/depttree")
    @Override
    //@PreAuthorize("hasAuthority('system:getDeptTree')")
    public QueryDeptTreeResponse getDeptTree() {
        return systemService.getDeptTree();
    }

}

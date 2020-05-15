package com.bishe.manage_user.service.impl;

import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import com.bishe.framework.domain.ucenter.response.QueryPageResponse;
import com.bishe.manage_user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testIUserService {
    @Autowired
    UserService userService;

    @Test
    public void testGetUserExt(){
        String username = "dlice";
        BsUserExt userExt = userService.getBsUserExt(username);
        if(userExt == null)
            System.out.println("用户不存在");
        else
            System.out.println(userExt.toString() + "username" + userExt.getUsername());
    }

    @Test
    public void testuserlist(){

        QueryPageRequest queryPageRequest = new QueryPageRequest();
        queryPageRequest.setPageNum(1);
        queryPageRequest.setPageSize(10);
//        queryPageRequest.setPid("001");
//        queryPageRequest.setPhone("17795793350");
        QueryPageResponse userlist = userService.userlist(queryPageRequest);
        System.out.println(userlist);
    }
}

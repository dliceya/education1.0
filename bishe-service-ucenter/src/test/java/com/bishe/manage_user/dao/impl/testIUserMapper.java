package com.bishe.manage_user.dao.impl;

import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class testIUserMapper {

    @Autowired
    private IUserDao iUserMapper;

    @Test
    public void testIp(){

    }

    @Test
    public void testgetUserList(){
        QueryPageRequest request = new QueryPageRequest();
        //request.setUsername("dlice");
//        request.setPageSize(2);
//        request.setPageNum(2);

        request.setBeginTime("2020-04-12 00:00:00");
        request.setEndTime("2020-06-10 00:00:00");

        List<BsUserExt> userList = iUserMapper.getUserList(request);
        System.out.println(userList);
    }
}

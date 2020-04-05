package com.bishe.manage_user.dao.impl;

import com.bishe.framework.domain.user.User;
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
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("dlice");
        loginUser.setPassword("158156");
        User user = iUserMapper.login(loginUser);
        System.out.println(user);
    }

    @Test
    public void testFindUser(){
        List<User> userList = iUserMapper.findUser();

        System.out.println(userList);
    }
}

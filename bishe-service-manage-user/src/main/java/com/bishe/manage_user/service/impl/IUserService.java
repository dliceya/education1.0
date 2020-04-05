package com.bishe.manage_user.service.impl;

import com.bishe.framework.domain.user.User;
import com.bishe.framework.domain.user.response.UserResult;

import java.util.List;

public interface IUserService {

    //获取所有用户
    public List<User> findUser();

    //按照用户信息查找用户
    public List<User> queryUser(User user);

    //用户登录查询
    public UserResult login(User user);
}

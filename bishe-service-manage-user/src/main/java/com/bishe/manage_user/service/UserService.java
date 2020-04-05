package com.bishe.manage_user.service;

import com.bishe.framework.domain.user.User;
import com.bishe.framework.domain.user.response.UserResult;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.manage_user.dao.impl.IUserDao;
import com.bishe.manage_user.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao iUserDao;

    @Override
    public List<User> findUser() {
        return iUserDao.findUser();
    }

    @Override
    public List<User> queryUser(User user) {
        return iUserDao.queryUser(user);
    }

    @Override
    public UserResult login(User loginUser) {
        User user = iUserDao.login(loginUser);
        UserResult userResult = new UserResult(CommonCode.FAIL, user);

        if(user != null){
            userResult = new UserResult(CommonCode.SUCCESS, user);
        }

        return userResult;
    }
}

package com.bishe.manage_user.dao.impl;

import com.bishe.framework.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IUserDao{

    //查找所有用户信息
    @Select("select * from user")
    public List<User> findUser();

    //条件查找用户
    @Select("select * from user")
    public List<User> queryUser(User user);

    //用户登录信息查询
    @Select("select * from user where username = #{username} and password = #{password}")
    public User login(User user);
}

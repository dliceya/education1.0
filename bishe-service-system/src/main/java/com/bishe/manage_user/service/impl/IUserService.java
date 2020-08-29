package com.bishe.manage_user.service.impl;

import com.bishe.framework.domain.system.request.OnlineRequest;
import com.bishe.framework.domain.system.response.OnlineResponse;
import com.bishe.framework.domain.ucenter.BsUser;
import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import com.bishe.framework.domain.ucenter.response.QueryPageResponse;
import com.bishe.framework.model.response.ResponseResult;

import java.util.List;

public interface IUserService {

    //根据用户名查找用户全部信息，一般不做调用
    public BsUserExt getBsUserExt(String username);

    //查询系统用户详细信息
    public QueryPageResponse userlist(QueryPageRequest pageRequest);

    //添加用户
    public boolean addUser(BsUser bsUser);

    //删除用户
    public int delUser(List<String> uids);

    //根据id查找用户
    public BsUser findUserById(String uid);

    //更新用户信息
    public boolean updateUser(BsUser user);

    //改密码
    ResponseResult reset(String uid, String password);

    //获取在线用户列表
    OnlineResponse online(OnlineRequest request);
}

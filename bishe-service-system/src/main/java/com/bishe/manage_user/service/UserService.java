package com.bishe.manage_user.service;

import com.bishe.framework.domain.system.Online;
import com.bishe.framework.domain.system.request.OnlineRequest;
import com.bishe.framework.domain.system.response.OnlineResponse;
import com.bishe.framework.domain.ucenter.BsUser;
import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import com.bishe.framework.domain.ucenter.response.QueryPageResponse;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.BCryptUtil;
import com.bishe.framework.utils.IdUtils;
import com.bishe.framework.utils.IpUtil;
import com.bishe.manage_user.dao.impl.IUserDao;
import com.bishe.manage_user.service.impl.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {


    @Autowired
    IUserDao iUserDao;

    //获取用户详细信息
    @Override
    public BsUserExt getBsUserExt(String username) {
        //查询该用户
        BsUser bsUser = iUserDao.getBsUserByUsername(username);
        if(bsUser == null)
            return null;
        //根据该用户的角色id查找该用户的权限列表
        List<String> permissionslist = iUserDao.getPermissionsByRid(bsUser.getRid());
        BsUserExt bsUserExt = new BsUserExt();
        BeanUtils.copyProperties(bsUser, bsUserExt);
        bsUserExt.setPermissions(permissionslist);
        return bsUserExt;
    }

    //多条件分页查询用户列表
    @Override
    public QueryPageResponse userlist(QueryPageRequest pageRequest) {

        //处理分页请求参数，使之符合数据库的要求
        pageRequest.setPageNum((pageRequest.getPageNum() - 1) * pageRequest.getPageSize());

        //定义消息返回体
        QueryPageResponse response = new QueryPageResponse();
        response.setCurrentPage(pageRequest.getPageNum());
        //根据查询天剑，设置结果数
        response.setTotal(iUserDao.getTotal(pageRequest));

        //查询用户列表
        List<BsUserExt> userList = iUserDao.getUserList(pageRequest);
        //遍历每个用户，将返回体的密码置为空，并查询其角色名，而不是直接返回角色id给用户
        userList.forEach(user -> {
            user.setPassword(user.getUid());
            user.setRid(iUserDao.getRoleName(user.getRid()));
        });
        response.setUserlist(userList);
        return response;
    }

    //添加用户
    @Override
    public boolean addUser(BsUser bsUser) {

        //获取请求request实例
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        bsUser.setUid(IdUtils.simpleUUID());
        bsUser.setPassword(BCryptUtil.encode(bsUser.getPassword()));
        bsUser.setCreateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        bsUser.setUpdateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        bsUser.setLoginIp(IpUtil.getIp(request));
        bsUser.setLoginDate(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));

        return iUserDao.addUser(bsUser) > 0;
    }

    @Override
    public int delUser(List<String> uids) {

        int delNum = 0;

        for(int i = 0; i < uids.size(); i++){
            delNum += iUserDao.delUser(uids.get(i));
        }
        return delNum;
    }

    @Override
    public BsUser findUserById(String uid) {
        return iUserDao.findUserById(uid);
    }

    @Override
    public boolean updateUser(BsUser user) {

        user.setUpdateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));

        return iUserDao.updateUser(user) > 0;
    }

    @Override
    public ResponseResult reset(String uid, String password) {

        String pass = BCryptUtil.encode(password);
        ResponseResult result;
        if(iUserDao.reset(uid, pass) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        } else result = new ResponseResult(CommonCode.SUCCESS);

        return result;
    }

    @Override
    public OnlineResponse online(OnlineRequest request) {

        request.setPageNum((request.getPageNum() - 1) * request.getPageSize());

        OnlineResponse response;

        List<Online> onlineList = iUserDao.onlineList(request);

        if(onlineList.size() > 0){
            response = new OnlineResponse(CommonCode.SUCCESS, onlineList);
        }else response = new OnlineResponse(CommonCode.FAIL, null);

        response.setTotal(iUserDao.getOnlineTotal(request));

        return response;

    }
}

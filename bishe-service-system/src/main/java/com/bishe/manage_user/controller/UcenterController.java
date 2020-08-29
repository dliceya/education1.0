package com.bishe.manage_user.controller;

import com.bishe.api.ucenter.UcenterControllerApi;
import com.bishe.framework.domain.system.request.OnlineRequest;
import com.bishe.framework.domain.system.response.OnlineResponse;
import com.bishe.framework.domain.ucenter.BsUser;
import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import com.bishe.framework.domain.ucenter.response.QueryPageResponse;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.manage_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ucenter")
@CrossOrigin
public class UcenterController implements UcenterControllerApi {

    @Autowired
    UserService userService;

    @Override
    //@PreAuthorize("hasAuthority('ucenter:getuserext')")
    @RequestMapping("/getuserext")
    public BsUserExt getUserExt(@RequestParam("username") String username) {
        return userService.getBsUserExt(username);
    }

    @PreAuthorize("hasAuthority('ucenter:reset')")
    @RequestMapping("/reset")
    public ResponseResult reset(@RequestParam String uid, @RequestParam String password){
        return userService.reset(uid, password);
    }

    @Override
    @PreAuthorize("hasAuthority('ucenter:list')")
    @RequestMapping("/userlist")
    public QueryPageResponse queryUser(@RequestBody QueryPageRequest pageRequest){
        return userService.userlist(pageRequest);
    }

    @RequestMapping("finduser")
    @PreAuthorize("hasAuthority('ucenter:finduser')")
    public BsUser getUser(@RequestParam("uid") String uid){
        return userService.findUserById(uid);
    }

    @RequestMapping("adduser")
    @PreAuthorize("hasAuthority('ucenter:add')")
    public ResponseResult addUser(@RequestBody BsUser bsUser){
        if(userService.addUser(bsUser)){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @RequestMapping("deluser")
    @PreAuthorize("hasAuthority('ucenter:del')")
    public ResponseResult delUser(@RequestBody List<String> uids){
        if(userService.delUser(uids) > 0){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @RequestMapping("updateuser")
    @PreAuthorize("hasAuthority('ucenter:update')")
    public ResponseResult updateUser(@RequestBody BsUser user){
        if(userService.updateUser(user)){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @RequestMapping("/online")
    public OnlineResponse online(@RequestBody OnlineRequest request){
        return userService.online(request);
    }
}

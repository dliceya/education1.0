package com.bishe.manage_user.controller;

import com.bishe.api.user.UserControllerApi;
import com.bishe.framework.domain.user.User;
import com.bishe.framework.domain.user.request.QueryPageRequest;
import com.bishe.framework.domain.user.response.UserResult;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.QueryResponseResult;
import com.bishe.framework.model.response.QueryResult;
import com.bishe.manage_user.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController implements UserControllerApi {

    @Autowired
    IUserService iUserService;

    @Override
    @RequestMapping("list/{page}/{size}")
    public QueryResponseResult findAll(@PathVariable("page") int page,@PathVariable("size") int size, QueryPageRequest queryPageRequest) {


        List<User> list = iUserService.findUser();

        QueryResult<User> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(list.size());

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        System.out.println(queryPageRequest.toString());
        System.out.println("page: " + page + "size: " + size);
        return queryResponseResult;
    }

    @Override
    public UserResult addUser(User user) {
        return null;
    }

    @Override
    @RequestMapping("/login")
    public UserResult login(@RequestBody User loginUser) {
        System.out.println(1);
        return iUserService.login(loginUser);
    }
}

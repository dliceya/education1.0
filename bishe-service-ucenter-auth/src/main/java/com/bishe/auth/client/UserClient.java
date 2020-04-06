package com.bishe.auth.client;

import com.bishe.framework.client.BsServiceList;
import com.bishe.framework.domain.ucenter.BsUserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = BsServiceList.Bs_SERVICE_UCENTER)
public interface UserClient {

    //根据账号查询用户信息
    @RequestMapping("/ucenter/getuserext")
    public BsUserExt getUserExt(@RequestParam("username") String username);
}

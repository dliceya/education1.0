package com.bishe.auth.client;

import com.bishe.framework.client.BsServiceList;
import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = BsServiceList.Bs_SERVICE_UCENTER)
public interface UserClient {

    //根据账号查询用户信息
    //headers = "Authorization=Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJxcSI6IjIyNTEyODUzMjYiLCJ1aWQiOiIwMDIiLCJ1c2VyX25hbWUiOiJsaXNpIiwic2NvcGUiOlsiYXBwIl0sInBpZCI6IjIiLCJwb3dlciI6InRlc3QiLCJleHAiOjE1ODk0MjE5NzcsImF1dGhvcml0aWVzIjpbImdldHVzZXJleHQiXSwianRpIjoiZGJkMDNhMGEtMmNkMC00MjAwLWJmOWQtMWE5YzgxOWFmOTRkIiwiY2xpZW50X2lkIjoiWGNXZWJBcHAiLCJ1c2VybmFtZSI6Imxpc2kifQ.cxx19EnxqSkFheO_1krArW8uIVR7OYFZXt3158dT0PsDvaIZnL5pew9BDVZQFkDhCRDswtbvf0H_4G0VgdGhQ26qRY2QBJjHA-O6fFA3BKZhZPrlF5-QLgx-kk-iaRul99lXLtomz90pFATok9oUAOIttiidflzanPFH1gJoiFn9JoagQPa5xoPR399k2eBGj3Bg5hPqKnBajTdxDqhwAT9BK_B9gr0aKGY7bErg175HHKqsRLxMJtZp3zj7scZ93v1-hU70f4vJdKupLjHt_2JQw1oD65MzkvLvZKwP4G_JiDGhKeCKHDE1d6aL53fsbw2oZKy-VP1iuMUV2_Q1Pw")

    @RequestMapping("/ucenter/getuserext")
            public BsUserExt getUserExt(@RequestParam("username") String username);
}

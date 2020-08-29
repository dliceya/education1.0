package com.bishe.system.controller;

import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.PullResult;
import com.bishe.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/server")
public class ServerPush {

    private ArrayList<String> messageList = new ArrayList<>();

    @GetMapping("/push")
    public ResponseResult pushMessage(@RequestParam("message") String message){
        ResponseResult result;

        if(messageList.add(message)){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else {
            result = new ResponseResult(CommonCode.FAIL);
        }

        return result;
    }

    @GetMapping("/pull")
    public PullResult pullMessage(){

        String message = "";
        PullResult result;

        if(messageList.size() > 0){
            message = messageList.get(0);
            messageList.remove(0);
        }
        if(StringUtils.isEmpty(message)){
            result = new PullResult(false, 100, message);
        }else{
            result = new PullResult(true, 200, message);
        }
        return result;

    }
}

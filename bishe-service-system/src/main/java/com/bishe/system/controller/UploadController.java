package com.bishe.system.controller;

import com.bishe.framework.domain.system.response.Upload;
import com.bishe.system.service.UploadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/upload")
public class UploadController {

    private final UploadService service;

    public UploadController(UploadService service) {
        this.service = service;
    }

    @GetMapping("/error")
    public Upload getErrorData(@RequestParam String name){
        return service.getData(name);
    }

    @GetMapping("/judge")
    public List<String> getJudge(){
        return service.getJudge();
    }
}

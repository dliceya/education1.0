package com.bishe.system.controller;

import com.bishe.framework.domain.record.Record;
import com.bishe.framework.domain.system.request.QueryRecordRequest;
import com.bishe.framework.domain.system.response.QueryRecordResponse;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.system.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    //系统菜单列表
    @RequestMapping("/list")
    public QueryRecordResponse getList(@RequestBody QueryRecordRequest request){
        return recordService.getList(request);
    }
    //新增菜单
    @RequestMapping("/addRecord")
    public ResponseResult addRecord(@RequestBody Record record){
        return recordService.addRecord(record);
    }

    //删除菜单
    @RequestMapping("delRecord")
    public ResponseResult delRecord(@RequestBody List<String> rids){
        return recordService.delRecord(rids);
    }

    //结束履历状态
    @GetMapping("endStatus")
    public ResponseResult endStatus(@RequestParam String rid){
        return recordService.endStatus(rid);
    }

}

package com.bishe.system.service;

import com.bishe.framework.domain.record.Record;
import com.bishe.framework.domain.system.request.QueryRecordRequest;
import com.bishe.framework.domain.system.response.QueryRecordResponse;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.IdUtils;
import com.bishe.system.dao.RecordDao;
import com.bishe.system.service.impl.IRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService implements IRecordService {

    private final RecordDao RecordDao;

    public RecordService(RecordDao RecordDao) {
        this.RecordDao = RecordDao;
    }

    private Map<String, String> typeMap = new LinkedHashMap<>();

    @Override
    public QueryRecordResponse getList(QueryRecordRequest request) {
        typeMap.put("1", "使用");
        typeMap.put("2", "封存");
        typeMap.put("3", "维修");
        typeMap.put("4", "故障");
        typeMap.put("5", "启封");

        QueryRecordResponse response;
        request.setPageNum((request.getPageNum() - 1) * request.getPageSize());
        List<String> equipIds = null;

        //若用户输入了装备名，则根据该名称模糊查询准备id，回写到装备名中
        if(!StringUtils.isEmpty(request.getEname())){
            equipIds = RecordDao.getEquipId(request.getEname());
        }
        if(equipIds != null) {
            request.setEname(equipIds.get(0));
        }

        //查询记录
        List<Record> recordsList = RecordDao.getList(request);

        recordsList.forEach(item -> {
            item.setEid(RecordDao.getEquipName(item.getEid()));
            item.setType(typeMap.get(item.getType()));
        });

        if(recordsList.size() > 0){
            response = new QueryRecordResponse(CommonCode.SUCCESS, recordsList);
            response.setTotal(RecordDao.getTotal());
        } else {
            response = new QueryRecordResponse(CommonCode.FAIL, null);
            response.setTotal(0);
        }

        return response;
    }

    @Override
    public ResponseResult addRecord(Record record) {
        ResponseResult result;

        record.setCreateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        record.setUpdateTime(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        record.setTime(0);
        record.setRid(IdUtils.simpleUUID());

        if(RecordDao.addRecord(record) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);

        return result;
    }

    @Override
    public ResponseResult delRecord(List<String> rids) {
        ResponseResult result;
        int count = 0;
        for (String rid : rids) {
            count += RecordDao.delRecord(rid);
        }
        if(count > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);
        return result;
    }

    @Override
    public ResponseResult endStatus(String rid) {
        ResponseResult result;
        Record record = RecordDao.getRecord(rid);

        LocalDateTime endTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        LocalDateTime beginTime = record.getBeginTime();

        Period period = Period.between(beginTime.toLocalDate(), endTime.toLocalDate());

        int days = period.getDays();

        if(days <= 0) days = 1;
        if(RecordDao.endStatus(days,endTime,rid)){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);

        return result;
    }
}

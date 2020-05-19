package com.bishe.system.service;

import com.bishe.framework.domain.equipment.Equipment;
import com.bishe.framework.domain.system.request.EquipListRequest;
import com.bishe.framework.domain.system.response.QueryEquipResult;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.IdUtils;
import com.bishe.system.dao.IEquipDao;
import com.bishe.system.service.impl.IEquipService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class EquipService implements IEquipService {

    private final IEquipDao iEquipDao;
    private Calendar timeUtils = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

    public EquipService(IEquipDao iEquipDao) {
        this.iEquipDao = iEquipDao;
    }

    @Override
    public QueryEquipResult getList(EquipListRequest request) {
        QueryEquipResult result;
        request.setPageNum((request.getPageNum() - 1) * request.getPageSize());

        List<Equipment> menuList = iEquipDao.getList(request);

        if(menuList != null){
            menuList.forEach(item -> item.setById(iEquipDao.getDeptName(item.getById())));

            result = new QueryEquipResult(CommonCode.SUCCESS, menuList);
            result.setTotal(iEquipDao.getTotal());
        } else {
            result = new QueryEquipResult(CommonCode.FAIL, null);
            result.setTotal(0);
        }

        return result;
    }

    @Override
    public Equipment getEquipByid(String eid) {
        return iEquipDao.getEquipByid(eid);
    }

    @Override
    public ResponseResult addEquip(Equipment equipment) {
        ResponseResult result;

        equipment.setDeliverTime(timeUtils.getTime());
        equipment.setEid(IdUtils.simpleUUID());

        if(iEquipDao.addEquip(equipment) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);

        return result;
    }

    @Override
    public ResponseResult updateEquip(Equipment equipment) {
        ResponseResult result;

        if(iEquipDao.updateEquip(equipment) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);
        return result;
    }

    @Override
    public ResponseResult delEquip(List<String> eids) {
        ResponseResult result;
        int count = 0;
        for (String eid : eids) {
            count += iEquipDao.delEquip(eid);
        }
        if(count > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);
        return result;
    }

    @Override
    public boolean check(String num) {
        return iEquipDao.check(num) > 0;
    }
}

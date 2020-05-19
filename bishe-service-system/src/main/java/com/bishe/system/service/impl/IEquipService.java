package com.bishe.system.service.impl;

import com.bishe.framework.domain.equipment.Equipment;
import com.bishe.framework.domain.system.request.EquipListRequest;
import com.bishe.framework.domain.system.response.QueryEquipResult;
import com.bishe.framework.model.response.ResponseResult;

import java.util.List;

public interface IEquipService {

    //获取准备列表
    QueryEquipResult getList(EquipListRequest request);

    //通过id查询装备
    Equipment getEquipByid(String eid);

    //添加装备
    ResponseResult addEquip(Equipment equipment);

    //更新装备
    ResponseResult updateEquip(Equipment equipment);

    //删除装备
    ResponseResult delEquip(List<String> eids);

    boolean check(String num);
}

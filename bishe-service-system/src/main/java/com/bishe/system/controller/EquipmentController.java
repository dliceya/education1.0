package com.bishe.system.controller;

import com.bishe.framework.domain.equipment.Equipment;
import com.bishe.framework.domain.system.request.EquipListRequest;
import com.bishe.framework.domain.system.response.QueryEquipResult;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.system.service.EquipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equip")
public class EquipmentController {

    private final EquipService equipService;

    public EquipmentController(EquipService equipService) {
        this.equipService = equipService;
    }


    //新增装备信息
    @RequestMapping("/addEquip")
    public ResponseResult addEquip(@RequestBody Equipment equipment){
        return equipService.addEquip(equipment);
    }

    //更新装备
    @RequestMapping("/updateEquip")
    public ResponseResult updateEquip(@RequestBody Equipment equipment){
        return equipService.updateEquip(equipment);
    }

    //删除菜单
    @RequestMapping("/delEquip")
    public ResponseResult delEquip(@RequestBody List<String> eids){
        return equipService.delEquip(eids);
    }

    //根据id查询菜单
    @GetMapping("/getequip")
    public Equipment getMenuByid(@RequestParam String eid){
        return equipService.getEquipByid(eid);
    }

    //系统装备列表
    @RequestMapping("/list")
    public QueryEquipResult getList(@RequestBody EquipListRequest request){
        return equipService.getList(request);
    }

    //验证装备编号是否存在
    @GetMapping("check")
    public boolean check(@RequestParam String num){
        return equipService.check(num);
    }

}

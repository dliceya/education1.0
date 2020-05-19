package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.equipment.Equipment;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class QueryEquipResult extends ResponseResult {
    public List<Equipment> equipList;

    public int total;

    public QueryEquipResult(ResultCode resultCode, List<Equipment> equipList){
        super(resultCode);
        this.equipList = equipList;
    }
}

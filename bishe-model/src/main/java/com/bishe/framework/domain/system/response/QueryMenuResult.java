package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.system.Menu;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryMenuResult extends ResponseResult{
    public List<Menu> menuList;

    public int total;

    public QueryMenuResult(ResultCode resultCode, List<Menu> menuList){
        super(resultCode);
        this.menuList = menuList;
    }
}

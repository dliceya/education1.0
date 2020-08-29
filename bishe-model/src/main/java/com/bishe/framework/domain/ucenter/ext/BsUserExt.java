package com.bishe.framework.domain.ucenter.ext;

import com.bishe.framework.domain.ucenter.BsUser;
import lombok.Data;

import java.util.List;

@Data
public class BsUserExt extends BsUser {

    //权限信息
    private List<String> permissions;

    //所属部门名称
    private String deptName;

}

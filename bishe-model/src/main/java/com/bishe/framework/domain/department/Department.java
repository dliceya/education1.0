package com.bishe.framework.domain.department;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Department {
    //部门id
    private String pid;

    //部门名称
    private String deptName;

    //子部门列表
    private List<Department>  child;

    //父部门id
    private String parent;

    //祖级列表
    private String ancestors;

    //负责人id
    private String leader;

    //电话
    private String phone;

    //部门状态，0代表不可用
    private String status;

    //显示顺序
    private String orderNum;

}

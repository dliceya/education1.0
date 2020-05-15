package com.bishe.framework.domain.ucenter;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class OnlineUser {
    //会话编号-->用户名加盐（登录时间）随机生成
    private String tokenId;

    //部门名称
    private String deptName;

    //用户昵称
    private String name;

    //登录ip
    private String ipAddr;

    //登录地址
    private String loginLocation;

    //浏览器类型
    private String browser;

    //操作系统
    private String os;

    //登录时间
    private Date loginTime;

    //用户类型
    private String uType;
}

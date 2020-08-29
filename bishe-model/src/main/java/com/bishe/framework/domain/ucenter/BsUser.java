package com.bishe.framework.domain.ucenter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BsUser {
    //用户Id, 32位字符串
    private String uid;

    //用户名，length = 20;
    private String username;

    //密码，length = 100;
    private String password;

    //用户昵称
    private String name;

    //用户QQ号码，用来获取用户头像
    private String qq;

    //用户角色
    private String rid;

    //部门id
    private String pid;

    //用户创建时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //用户信息更新事件
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //用户邮箱
    private String email;

    //是否启用用户，"1"为启用
    private String status;

    //用户最后一次登录ip
    private String loginIp;

    //用户最后一次登录时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;

    //用户手机号
    private String phone;

    //用户备注
    private String remark;

}

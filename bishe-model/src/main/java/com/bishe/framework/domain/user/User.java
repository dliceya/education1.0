package com.bishe.framework.domain.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private int uid;
    private String username;
    private String password;
    private Integer power;
    private int pid;

    public User() {
    }

    public User(int uid, String userName, String passWord, Integer power, int pid) {
        this.uid = uid;
        this.username = userName;
        this.password = passWord;
        this.power = power;
        this.pid = pid;
    }
}

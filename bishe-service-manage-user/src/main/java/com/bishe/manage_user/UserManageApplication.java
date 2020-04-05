package com.bishe.manage_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.bishe.api"})//扫描接口
@ComponentScan(basePackages = {"com.bishe.manage_user"})//扫描本项目下的所有类
@ComponentScan(basePackages = {"com.bishe.framework"})//扫描common包下的类
@SpringBootApplication
public class UserManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManageApplication.class, args);
    }
}

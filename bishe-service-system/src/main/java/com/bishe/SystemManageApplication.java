package com.bishe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"com.bishe.api"})//扫描接口
@ComponentScan(basePackages = {"com.bishe.system"})//扫描本项目下的所有类
@ComponentScan(basePackages = {"com.bishe"})//扫描common包下的类
@EntityScan("com.bishe.framework")
@SpringBootApplication

public class SystemManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemManageApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}

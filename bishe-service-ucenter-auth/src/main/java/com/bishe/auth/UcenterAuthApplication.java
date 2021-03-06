package com.bishe.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients
@EntityScan("com.bishe.framework.domain.ucenter")
@ComponentScan(basePackages={"com.bishe.api"})//扫描接口
@ComponentScan(basePackages={"com.bishe.framework"})//扫描common下的所有类
@ComponentScan(basePackages={"com.bishe.auth"})
@ComponentScan("com.bishe.auth")
@SpringBootApplication
public class UcenterAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterAuthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}

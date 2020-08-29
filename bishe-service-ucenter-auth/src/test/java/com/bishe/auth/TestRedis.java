//package com.bishe.config;
//
//import com.alibaba.fastjson.JSON;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class TestRedis {
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//
//    //创建jwt令牌
//    @Test
//    public void testRedis(){
//        //定义key
//        String key = "user_token:9734b68f‐cf5e‐456f‐9bd6‐df578c711390";
//        //定义value
//        Map<String, String> value = new HashMap<>();
//        value.put("jwt","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHQiOiIxIiwicm9sZXMiOiJyMDEscjAyIiwibmFtZSI6Im1ydCIsImlkIjoiMTIzIn0.KK7_67N5d1Dthd1PgDHMsbi0UlmjGRcm_XJUUwseJ2eZyJJWoPP2IcEZgAU3tUaaKEHUf9wSRwaDgwhrwfyIcSHbs8oy3zOQEL8j5AOjzBBs7vnRmB7DbSaQD7eJiQVJOXO1QpdmEFgjhc_IBCVTJCVWgZw60IEW1_Lg5tqaLvCiIl26K48pJB5f‐le2zgYMzqR1L2LyTFkq39rG57VOqqSCi3dapsZQd4ctq95SJCXgGdrUDWtD52rp5o6_0uq‐mrbRdRxkrQfsa1j8C5IW2‐T4eUmiN3f9wF9JxUK1__XC1OQkOn‐ZTBCdqwWIygDFbU7sf6KzfHJTm5vfjp6NIA");
//        value.put("refresh", "testRefresh");
//        String valueString = JSON.toJSONString(value);
//        //存储数据
//        stringRedisTemplate.boundValueOps(key).set(valueString, 30, TimeUnit.SECONDS);
//
//        //检验key是否存在
//        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
//        System.out.println(expire);
//        //获取数据
//        String string = stringRedisTemplate.opsForValue().get(key);
//        System.out.println(string);
//    }
//}

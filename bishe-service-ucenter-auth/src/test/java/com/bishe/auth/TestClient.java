//package com.bishe.auth;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.Base64Utils;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.DefaultResponseErrorHandler;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.Map;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class TestClient {
//
//    //@Autowired
//    //LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Test
//    public void testClient(){
//        //从Eureka中获取认证服务的地址
//        //ServiceInstance choose = loadBalancerClient.choose("xc-server-ucenter-auth");
//        //URI uri = choose.getUri();
//        //令牌的申请地址 40400/auth/oauth/token
//
//        String authUri = "http://localhost:40400/auth/oauth/token";//uri + "/auth/oauth/token"
//
//        //定义header
//        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
//        String httpBasic = getHttpBasic("XcWebApp", "XcWebApp");
//        header.add("Authorization", httpBasic);
//
//        //定义body
//        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "password");
//        body.add("username", "dlic7");
//        body.add("password", "1581546");
//
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
//        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables
//
//        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
//            @Override
//            public void handleError(ClientHttpResponse response) throws IOException {
//                if(response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
//                    super.handleError(response);
//                }
//            }
//        });
//
//        ResponseEntity<Map> exchange = restTemplate.exchange(authUri, HttpMethod.POST, httpEntity, Map.class);
//
//        //申请令牌信息
//        Map responseBody = exchange.getBody();
//        System.out.println(responseBody);
//    }
//
//    //获取HttpBasic的串
//    //获取httpbasic的串
//    private String getHttpBasic(String clientId,String clientSecret){
//        String string = clientId+":"+clientSecret;
//        //将串进行base64编码
//        byte[] encode = Base64Utils.encode(string.getBytes());
//        return "Basic "+new String(encode);
//    }
//}

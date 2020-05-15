//package com.bishe.auth;
//
//import com.alibaba.fastjson.JSON;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.jwt.crypto.sign.RsaSigner;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.security.KeyPair;
//import java.security.interfaces.RSAPrivateKey;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class TestJwt {
//
//    @Test
//    public void testJWT(){
//        //秘钥文件
//        String keystore = "xc.keystore";
//        //秘钥库密码
//        String keystore_password = "xuechengkeystore";
//
//        //秘钥别名
//        String alias = "xckey";
//        //秘钥的访问密码
//        String key_password = "xuecheng";
//
//
//        //秘钥库文件路径
//        ClassPathResource classPathResource = new ClassPathResource(keystore);
//
//        //秘钥工厂
//        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(classPathResource, keystore_password.toCharArray());
//
//        //密钥对
//        KeyPair keyPair = factory.getKeyPair(alias, key_password.toCharArray());
//
//        //获取私钥
//        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
//
//        //生成JWT令牌
//        //jwt令牌的内容
//        Map<String,String > jwtBody = new HashMap<>();
//        jwtBody.put("name", "dlice");
//        String jwtBodyString = JSON.toJSONString(jwtBody);
//        Jwt jwt = JwtHelper.encode(jwtBodyString, new RsaSigner(privateKey));
//
//        //生成JWT令牌编码
//        String jwtEncode = jwt.getEncoded();
//        System.out.println("jwtEncode: " + jwtEncode);
//        System.out.println("privateKey: " + privateKey.getFormat());
//        System.out.println("publicKey: " + keyPair.getPublic().getFormat());
//
//    }
//
//    @Test
//    public void testVerify(){
//        //公钥
//        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnASXh9oSvLRLxk901HANYM6KcYMzX8vFPnH/To2R+SrUVw1O9rEX6m1+rIaMzrEKPm12qPjVq3HMXDbRdUaJEXsB7NgGrAhepYAdJnYMizdltLdGsbfyjITUCOvzZ/QgM1M4INPMD+Ce859xse06jnOkCUzinZmasxrmgNV3Db1GtpyHIiGVUY0lSO1Frr9m5dpemylaT0BV3UwTQWVW9ljm6yR3dBncOdDENumT5tGbaDVyClV0FEB1XdSKd7VjiDCDbUAUbDTG1fm3K9sx7kO1uMGElbXLgMfboJ963HEJcU01km7BmFntqI5liyKheX+HBUCD4zbYNPw236U+7QIDAQAB-----END PUBLIC KEY-----";
//
//        //jwt令牌
//        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZGxpY2UifQ.ZT8qKOFaMgFnTB-yKAFIXrgJOGb4oC3P3JoujJ_jcHCqgI4r6FWtuHDKTn9eDU2McYIIoJjiYXLY_qs1Xn0mPmiQ5SARdaLqL9VdnZMXT0SJo0-0OB8XdcLziITvfIGytitLMHrupbd6rOot40gmL_BkHVS8-p2M1NlX2fjx-6HBz5auPiPRB7YOhMxbqDDt9NP6_yWOd7KlM4p_Mkw0wjOStf1XENPVyTsvWpcGlKu-rpIWfC3q24sXMRB3o6FSWMYiYkvAIlNRLL60i3ds6i6RcaUIelHsDb3Yh_HuywjBbYYK9dwezrtdnAm1K-jPLOZRdbLgB9DoQDalWuha9A";
//
//        //校验jwt令牌
//        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
//        //拿到令牌自定义内容
//        String claims = jwt.getClaims();
//        System.out.println(claims);
//    }
//}

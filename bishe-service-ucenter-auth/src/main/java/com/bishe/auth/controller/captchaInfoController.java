package com.bishe.auth.controller;

import com.bishe.api.auth.captchaInfoControllerApi;
import com.bishe.framework.domain.ucenter.response.AjaxResult;
import com.bishe.framework.domain.ucenter.response.AuthCode;
import com.bishe.framework.exception.ExceptionCast;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.utils.Base64;
import com.bishe.framework.utils.IdUtils;
import com.bishe.framework.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class captchaInfoController implements captchaInfoControllerApi {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = "verifyKey" + uuid;

        //将验证码存入Redis，有效期默认1分钟
        redisTemplate.boundValueOps(verifyKey).set(verifyCode,1, TimeUnit.MINUTES);
        Long expire = redisTemplate.getExpire(verifyKey, TimeUnit.SECONDS);
        if(expire <= 0){
            ExceptionCast.cast(AuthCode.REDIS_ERROR);
        }

        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        String img = Base64.encode(stream.toByteArray());
        try
        {
            AjaxResult result = new AjaxResult(CommonCode.SUCCESS, uuid, img);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            AjaxResult result = new AjaxResult(CommonCode.CAPTCHAERROR, null, null);
            return result;
        }
        finally
        {
            stream.close();
        }
    }
}

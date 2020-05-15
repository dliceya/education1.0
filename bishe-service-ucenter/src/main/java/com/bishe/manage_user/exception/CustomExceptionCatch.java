package com.bishe.manage_user.exception;

import com.bishe.framework.exception.ExceptionCatch;
import com.bishe.framework.model.response.CommonCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;


/**
 * 用户中心异常类型处理.
 * 定义异常类型所对应的代码
 */
@ControllerAdvice
public class CustomExceptionCatch extends ExceptionCatch {

    static {
        //用户无权限访问某个方法是抛出该异常
        builder.put(AccessDeniedException.class, CommonCode.UNAUTHORISE);
    }

}

package com.akagiyui.edgeconnect.config;

import com.akagiyui.edgeconnect.entity.ResponseResult;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.utils.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 * @author AkagiYui
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 全局自定义异常处理
     * @param e 异常
     * @return 返回相应
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseResult<Object> customException(CustomException e) {
        return ResponseResult.responseEnum(e.getStatus());
    }

    /**
     * 404异常处理
     * @param e 异常
     * @return 返回相应
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseResult<Object> unknownException(NoHandlerFoundException ignored) {
        return ResponseResult.responseEnum(ResponseEnum.NOT_FOUND);
    }

    /**
     * 全局其他异常处理
     * @param e 异常
     * @return 返回相应
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> unknownException(Exception e) {
        e.printStackTrace();
        return ResponseResult.internalError();
    }
}

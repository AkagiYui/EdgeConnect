package com.akagiyui.edgeconnect.config;

import com.akagiyui.edgeconnect.entity.ResponseResult;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.utils.ResponseEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
     * 全局异常处理
     * @param e 异常
     * @return 返回相应
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<?> unknownException(Exception e) {
        // 404 异常处理
        if (e instanceof NoHandlerFoundException) {
            return ResponseResult.response(ResponseEnum.NOT_FOUND);
        }
        // 自定义异常处理
        if (e instanceof CustomException ce) {
            return ResponseResult.response(ce.getStatus());
        }
        // 认证异常处理
        if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException) {
            return ResponseResult.response(ResponseEnum.UNAUTHORIZED);
        }
        // 参数校验异常处理
        if (e instanceof MethodArgumentNotValidException ae) {
            FieldError fieldError = ae.getBindingResult().getFieldError();
            if (fieldError != null) {
                return ResponseResult.response(ResponseEnum.BAD_REQUEST, fieldError.getDefaultMessage());
            }
            return ResponseResult.response(ResponseEnum.BAD_REQUEST);
        }
        e.printStackTrace();
        return ResponseResult.response(ResponseEnum.INTERNAL_ERROR);
    }

}

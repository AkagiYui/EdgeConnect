package com.akagiyui.edgeconnect.config;

import com.akagiyui.edgeconnect.entity.ResponseResult;
import com.akagiyui.edgeconnect.exception.CustomException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.akagiyui.edgeconnect.component.ResponseEnum.*;

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
            return ResponseResult.response(NOT_FOUND);
        }
        // 自定义异常处理
        if (e instanceof CustomException ce) {
            return ResponseResult.response(ce.getStatus());
        }
        // 认证异常处理
        if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException) {
            return ResponseResult.response(UNAUTHORIZED);
        }
        // 参数校验异常处理
        if (e instanceof MethodArgumentNotValidException ae) {
            FieldError fieldError = ae.getBindingResult().getFieldError();
            if (fieldError != null) {
                return ResponseResult.response(BAD_REQUEST, fieldError.getDefaultMessage());
            }
            return ResponseResult.response(BAD_REQUEST);
        }
        e.printStackTrace();
        return ResponseResult.response(INTERNAL_ERROR);
    }

}

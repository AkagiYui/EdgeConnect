package com.akagiyui.edgeconnect.config;

import com.akagiyui.edgeconnect.entity.ResponseResult;
import com.akagiyui.edgeconnect.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
     * 404 异常处理
     * @return 返回相应
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseResult<?> unknownException(NoHandlerFoundException ignored) {
        return ResponseResult.response(NOT_FOUND);
    }

    /**
     * 401 认证异常处理
     * @return 返回相应
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public ResponseResult<?> unknownException(InternalAuthenticationServiceException e) {
        return ResponseResult.response(UNAUTHORIZED, e.getMessage());
    }

    /**
     * 403 授权异常处理
     * @return 返回相应
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseResult<?> unknownException(AccessDeniedException e) {
        return ResponseResult.response(FORBIDDEN, e.getMessage());
    }

    /**
     * 全局异常处理
     * @param e 异常
     * @return 返回相应
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<?> unknownException(Exception e) {
        // 自定义异常处理
        if (e instanceof CustomException ce) {
            return ResponseResult.response(ce.getStatus());
        }
        // 认证异常处理
        if (e instanceof BadCredentialsException) {
            return ResponseResult.response(FORBIDDEN);
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

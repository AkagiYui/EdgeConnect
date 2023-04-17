package com.akagiyui.edgeconnect.config;

import com.akagiyui.edgeconnect.entity.ResponseResult;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author AkagiYui
 */

@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    // 异常处理
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> err(Exception exception){
        return ResponseResult.error(500, exception.getMessage());
    }

    // 正常返回的包装处理
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof ResponseResult) {
            return body;
        }
        // 包装返回的数据
        return ResponseResult.success(body);
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断是否需要执行 beforeBodyWrite 方法
        return true;
    }
}

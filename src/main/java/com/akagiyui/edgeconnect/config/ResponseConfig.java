package com.akagiyui.edgeconnect.config;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 响应配置
 * @author AkagiYui
 */
@Configuration
public class ResponseConfig implements WebMvcConfigurer {
    /**
     * 配置响应转换器
     * @param converters 转换器列表
     */
    @Override
    public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        // 移除默认的 StringHttpMessageConverter，避免返回的 String 类型被转义
        converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }
}

package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.request.LoginRequest;

/**
 * 登录 Service 接口
 * @author AkagiYui
 */
public interface LoginService {
    /**
     * 登录
     * @param request 登录请求体
     * @return jwt 字符串
     */
    String login(LoginRequest request);
}

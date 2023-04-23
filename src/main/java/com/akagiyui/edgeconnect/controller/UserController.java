package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterRequest;
import com.akagiyui.edgeconnect.entity.response.LoginResponse;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户控制器
 * @author AkagiYui
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    LoginService loginService;

    @Resource
    UserService userService;

    /**
     * 登录
     * @param user 用户
     * @return 返回token
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest user) {
        return new LoginResponse(loginService.login(user), null);
    }

    /**
     * 注册
     * @param registerRequest 注册请求体
     * @return 是否成功
     */
    @PostMapping("/register")
    public boolean register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}

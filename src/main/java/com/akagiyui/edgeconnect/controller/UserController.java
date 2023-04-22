package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.entity.response.LoginResponse;
import com.akagiyui.edgeconnect.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author AkagiYui
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    LoginService loginService;

    /**
     * 登录
     * @param user 用户
     * @return 返回token
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest user) {
        return new LoginResponse(loginService.login(user), null);
    }

}

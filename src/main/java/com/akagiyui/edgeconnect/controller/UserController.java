package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.component.limiter.Limit;
import com.akagiyui.edgeconnect.entity.request.EmailVerifyCodeRequireRequest;
import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterConfirmRequest;
import com.akagiyui.edgeconnect.entity.response.LoginResponse;
import com.akagiyui.edgeconnect.entity.response.UserInfoResponse;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户控制器
 * @author AkagiYui
 */
@RestController
@RequestMapping("/user")
@Slf4j
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
     * @param registerConfirmRequest 注册请求体
     * @return 是否成功
     */
    @PostMapping("/register")
    public boolean register(@RequestBody @Valid RegisterConfirmRequest registerConfirmRequest) {
        return userService.confirmRegister(registerConfirmRequest);
    }

    @PostMapping("/register/code")
    @Limit(key = "getVerifyCode", permitsPerSecond = 1, timeout = 1)
    public boolean getVerifyCode(@RequestBody @Valid EmailVerifyCodeRequireRequest verifyRequest) {
        return userService.sendEmailVerifyCode(verifyRequest);
    }

    /**
     * 获取自身信息
     * @return 自身信息
     */
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public UserInfoResponse getSelfInfo() {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userService.getUser(), userInfoResponse);
        return userInfoResponse;
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('user:test')")
    public String test() {
        return "test";
    }

    @GetMapping("/testno")
    @PreAuthorize("hasAuthority('user:no')")
    public String test233() {
        return "testno";
    }
}

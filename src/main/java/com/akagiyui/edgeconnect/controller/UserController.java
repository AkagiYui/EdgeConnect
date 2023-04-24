package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterRequest;
import com.akagiyui.edgeconnect.entity.response.LoginResponse;
import com.akagiyui.edgeconnect.entity.response.UserInfoResponse;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public boolean register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    /**
     * 获取自身信息
     * @return 自身信息
     */
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public UserInfoResponse getSelfInfo() {
        // 从 SecurityContextHolder 中获取用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userDetails.getUser(), userInfoResponse);
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

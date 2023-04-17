package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AkagiYui
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    LoginService loginService;

    @GetMapping("")
    public List<User> getUserInfo() {
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user) {
        return loginService.login(user);
    }
}

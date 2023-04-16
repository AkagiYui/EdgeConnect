package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AkagiYui
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("")
    public List<User> getUserInfo() {
        return userService.getAllUser();
    }

}

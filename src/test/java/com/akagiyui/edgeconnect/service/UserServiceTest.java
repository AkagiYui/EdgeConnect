package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.RegisterRequest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 用户 Service 接口测试
 * @author AkagiYui
 */
@SpringBootTest
@DisplayName("用户 Service 接口测试")
class UserServiceTest {

    @Resource
    UserService userService;

    /**
     * 添加用户
     */
    @Test
    @DisplayName("添加用户")
    void addUser() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testUser");
        registerRequest.setPassword("testPassword");
        registerRequest.setEmail("testEmail");
        registerRequest.setNickname("testNickname");
        assertTrue(userService.addUser(registerRequest));
    }

    /**
     * 获取所有用户
     */
    @Test
    @DisplayName("获取所有用户")
    void getAllUser() {
        List<User> users = userService.getAllUser();
        users.forEach(System.out::println);
    }

    @Test
    void isUserExist() {
        assertTrue(userService.isUserExist("testUser"));
    }
}

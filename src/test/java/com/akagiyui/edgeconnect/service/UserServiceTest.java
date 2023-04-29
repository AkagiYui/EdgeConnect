package com.akagiyui.edgeconnect.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * 用户 Service 接口测试
 * @author AkagiYui
 */
@SpringBootTest
@DisplayName("用户 Service 接口测试")
class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void isUserExist() {
        assertFalse(userService.isUserExist("testUser"));
    }
}

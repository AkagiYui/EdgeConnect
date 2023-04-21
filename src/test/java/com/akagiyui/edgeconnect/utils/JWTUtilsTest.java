package com.akagiyui.edgeconnect.utils;

import com.akagiyui.edgeconnect.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JWT 工具类测试
 * @author AkagiYui
 */
@SpringBootTest
@DisplayName("JWT 工具类测试")
class JWTUtilsTest {

    @Resource
    JWTUtils jwtUtils;

    /**
     * 测试 JWT 验证
     */
    @Test
    @DisplayName("测试 JWT 验证")
    void verifyJWT() {
        User user = new User();
        user.setId(233L);
        assertTrue(jwtUtils.verifyJWT(jwtUtils.createJWT(user)));
    }

    /**
     * 测试获取用户 ID
     */
    @Test
    @DisplayName("测试获取用户 ID")
    void getUserId() {
        User user = new User();
        user.setId(233L);
        String jwt = jwtUtils.createJWT(user);
        System.out.println(jwt);
        Long userId = jwtUtils.getUserId(jwt);
        System.out.println(userId);
        assertEquals(user.getId(), userId);
    }
}

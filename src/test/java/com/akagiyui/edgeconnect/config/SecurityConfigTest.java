package com.akagiyui.edgeconnect.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Security 配置测试
 * @author AkagiYui
 */
@DisplayName("Security 配置测试")
class SecurityConfigTest {

    /**
     * 测试密码加密
     */
    @Test
    @DisplayName("测试密码加密")
    void passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encoded = passwordEncoder.encode("123456");
        System.out.println(encoded);
        assertTrue(passwordEncoder.matches("123456", encoded));
    }
}

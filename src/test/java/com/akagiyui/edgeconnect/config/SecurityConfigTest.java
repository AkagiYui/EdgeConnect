package com.akagiyui.edgeconnect.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AkagiYui
 */

class SecurityConfigTest {

    @Test
    void passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encoded = passwordEncoder.encode("123456");
        System.out.println(encoded);
        assertTrue(passwordEncoder.matches("123456", encoded));
    }
}

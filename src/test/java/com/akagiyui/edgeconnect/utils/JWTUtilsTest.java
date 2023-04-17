package com.akagiyui.edgeconnect.utils;

import com.akagiyui.edgeconnect.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AkagiYui
 */

class JWTUtilsTest {

    @Test
    void createJWT() {
    }

    @Test
    void verifyJWT() {
        User user = new User();
        user.setId(233L);
        JWTUtils.verifyJWT(JWTUtils.createJWT(user));
    }

    @Test
    void getUserId() {
    }

    @Test
    void getUser() {
        User user = new User();
        user.setId(233L);
        String jwt = JWTUtils.createJWT(user);
        System.out.println(jwt);
        Long userId = JWTUtils.getUserId(jwt);
        System.out.println(userId);
        assertEquals(user.getId(), userId);
    }
}

package com.akagiyui.edgeconnect.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Pair;
import cn.hutool.jwt.JWT;
import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author AkagiYui
 */
@Component
public class JWTUtils {
    static byte[] key = "12456789".getBytes();
    static Pair<Integer, DateField> expireTime = Pair.of(2, DateField.HOUR);

    private static JWTUtils jwtUtils;

    @PostConstruct
    public void init() {
        jwtUtils = this;
    }

    /**
     * 生成密钥
     *
     * @return 密钥
     */
    public static String createJWT(User user) {
        return createJWT(user.getId());
    }

    public static String createJWT(LoginUserDetails user) {
        return createJWT(user.getUser());
    }

    public static String createJWT(Long userId) {
        DateTime currentTime = DateTime.now();
        return JWT.create()
                .setPayload("id", userId)
                .setKey(key)
                .setIssuedAt(currentTime)
                .setNotBefore(currentTime)
                .setExpiresAt(DateTime.now().offset(expireTime.getValue(), expireTime.getKey()))
                .sign();
    }

    /**
     * 验证密钥
     *
     * @param token 密钥
     * @return 是否有效
     */
    public static boolean verifyJWT(String token) {
        return JWT.of(token).setKey(key).verify();
    }

    /**
     * 获取密钥中的用户id
     *
     * @param token 密钥
     * @return 用户id
     */
    public static Long getUserId(String token) {
        return  JWT.of(token).setKey(key).getPayloads().getLong("id");
    }

    @Resource
    UserService userService;

    /**
     * 从密钥中获取用户
     * @param token 密钥
     * @return 用户
     */
    public User getUser(String token) {
        return userService.getById(getUserId(token));
    }
}

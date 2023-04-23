package com.akagiyui.edgeconnect.component;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Pair;
import cn.hutool.jwt.JWT;
import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT工具类
 * @author AkagiYui
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Component
public class JWTUtils {
    /**
     * 密钥
     */
    @Value("${edge.jwt.key}")
    private byte[] key;
    /**
     * 过期时间
     */
    static Pair<Integer, DateField> expireTime = Pair.of(2, DateField.HOUR);

    /**
     * 生成密钥
     * @param user 用户
     * @return 密钥
     */
    public String createJWT(User user) {
        return createJWT(user.getId());
    }

    /**
     * 生成密钥
     * @param user 用户
     * @return 密钥
     */
    public String createJWT(LoginUserDetails user) {
        return createJWT(user.getUser());
    }

    /**
     * 生成密钥
     * @param userId 用户id
     * @return 密钥
     */
    public String createJWT(Long userId) {
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
    public boolean verifyJWT(String token) {
        return JWT.of(token).setKey(key).verify();
    }

    /**
     * 获取密钥中的用户id
     *
     * @param token 密钥
     * @return 用户id
     */
    public Long getUserId(String token) {
        return  JWT.of(token).setKey(key).getPayloads().getLong("id");
    }
}

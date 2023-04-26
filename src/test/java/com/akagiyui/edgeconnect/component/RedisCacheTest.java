package com.akagiyui.edgeconnect.component;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AkagiYui
 */
@SpringBootTest
class RedisCacheTest {

    @Resource
    RedisCache redisCache;

    @Test
    void get() {
        redisCache.get("test");
    }
}

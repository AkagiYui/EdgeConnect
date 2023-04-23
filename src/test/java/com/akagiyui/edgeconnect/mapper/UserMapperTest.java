package com.akagiyui.edgeconnect.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户表 Mapper 接口测试
 * @author AkagiYui
 */
@SpringBootTest
@DisplayName("用户表 Mapper 接口测试")
class UserMapperTest {
    @Resource
    UserMapper userMapper;

    /**
     * 测试查询所有用户
     */
    @Test
    @DisplayName("测试查询所有用户")
    void selectList() {
        userMapper.selectList(null).forEach(System.out::println);
    }
}

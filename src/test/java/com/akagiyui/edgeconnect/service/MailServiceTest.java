package com.akagiyui.edgeconnect.service;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author AkagiYui
 */
@SpringBootTest
class MailServiceTest {

    @Resource
    MailService mailService;

    @Value("${edge.email.verify.timeout}")
    private long emailVerifyTimeout;

    @Test
    void sendEmailVerifyCode() {
        mailService.sendEmailVerifyCode("my@163.com", RandomUtil.randomNumbers(6), emailVerifyTimeout);
    }
}

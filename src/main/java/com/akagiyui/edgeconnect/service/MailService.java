package com.akagiyui.edgeconnect.service;

/**
 * 邮件 Service 接口
 * @author AkagiYui
 */
public interface MailService {
    void sendMail(String from, String to, String subject, String content);

    void sendEmailVerifyCode(String email, String verifyCode, Long timeout);
}

package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务 实现
 * @author AkagiYui
 */
@Service
public class MailServiceImpl implements MailService {
    @Resource
    JavaMailSender javaMailSender;

    @Resource
    TemplateEngine templateEngine;

    @Value("${edge.email.from}")
    String sendFrom;

    @Override
    public void sendEmailVerifyCode(String to, String code, Long timeout) {
        String subject = "Edge Connect 邮箱验证码";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sendFrom);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);

            // 构建邮件内容
            Context context = new Context();
            context.setVariable("code", code);
            context.setVariable("timeout", timeout);
            String content = templateEngine.process("mail_verify", context);
            mimeMessageHelper.setText(content, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.sxyhton.common.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sxyhton.common.domain.MailSiteDO;
import org.springframework.stereotype.Service;

public class MailUtils {
    /**
     * 向用户发送邮件
     * @param mailSiteDO 邮件参数
     */
    public static void sendMail(MailSiteDO mailSiteDO) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", mailSiteDO.getSmtpHost());
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mailSiteDO.getFrom()));
            message.setRecipient(RecipientType.TO, new InternetAddress(mailSiteDO.getRecipient()));
            message.setSubject(mailSiteDO.getSubject());
            message.setContent(mailSiteDO.getContent(), "text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect(mailSiteDO.getSmtpHost(), mailSiteDO.getUsername(), mailSiteDO.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败...");
        }
    }
}

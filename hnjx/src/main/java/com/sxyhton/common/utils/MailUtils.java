package com.sxyhton.common.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxyhton.common.domain.DictDO;
import com.sxyhton.common.service.DictService;
@Service
public class MailUtils {
	/**发送邮件服务器地址*/
    private static String smtp_host = "smtp.qq.com";
    /**邮箱账户*/
    private static String username = "hzof@qq.com"; // 
    /**邮箱授权码,邮箱设置里点击获取第三方发送工具的授权码*/
    private static String password = "exwfujofewnsbeea";
    /**使用当前账户,发件人*/
    private static String from = "hzof@qq.com";
    /***/
    public static String activeUrl = "http://localhost:9003/bos_fore/customer_activeMail";
    @Autowired private static DictService dictService;
    public static void setMailSite() {
    	List<DictDO> listByType = dictService.listByType("cmsMail");
    	for(DictDO dictDO:listByType) {
    		if("smtp_host".equals(dictDO.getName()))
    			smtp_host=dictDO.getValue();
    		if("username".equals(dictDO.getName()))
    			username=dictDO.getValue();
    		if("password".equals(dictDO.getName()))
    			password=dictDO.getValue();
    		if("from".equals(dictDO.getName()))
    			from=dictDO.getValue();
    	}
    }
    /**
     * 向用户发送邮件
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param to 目标邮箱
     */
    public static void sendMail(String subject, String content, String to) {
    	setMailSite();//重新从数据库获取数据
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtp_host);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect(smtp_host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败...");
        }
    }
}

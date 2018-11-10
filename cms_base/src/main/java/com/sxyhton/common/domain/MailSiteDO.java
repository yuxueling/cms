package com.sxyhton.common.domain;

/**
 * @Author：YXling Create In 9:39 2018/11/8
 */
public class MailSiteDO {
    /**发送邮件服务器地址*/
    private String smtpHost;
    /**授权邮箱账号*/
    private String username;
    /**邮箱授权码,邮箱设置里点击获取第三方发送工具的授权码*/
    private String password;
    /**使用当前账户,发件人*/
    private String from;
    /**收件人*/
    private String recipient;
    /**标题*/
    private String subject;
    /**内容*/
    private String content;


    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

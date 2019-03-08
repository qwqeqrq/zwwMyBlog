package com.core.blog.uitls;


import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/12/13/00013
 * @Description:
 */
@Component
public class EmailUtils {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    @Autowired
    private Environment env;


    private static String auth;

    @Value("${mailsmtpauth}")
    public void setAuth(String mailsmtpauth) {
        EmailUtils.auth = mailsmtpauth;
    }

    private static String host;

    @Value("${mailhost}")
    public void setHost(String mailhost) {
        EmailUtils.host = mailhost;
    }

    @Value("${mailtransportprotocol}")
    public void setProtocol(String mailtransportprotocol) {
        EmailUtils.protocol = mailtransportprotocol;
    }

    private static String protocol;

    @Value("${mailsmtpport}")
    public void setPort(String mailsmtpport) {
        EmailUtils.port = Integer.valueOf(mailsmtpport);
    }

    private static int port;

    @Value("${mailauthname}")
    public void setAuthName(String mailauthname) {
        EmailUtils.authName = mailauthname;
    }

    private static String authName;

    @Value("${mailauthpassword}")
    public void setPassword(String mailauthpassword) {
        EmailUtils.password = mailauthpassword;
    }

    private static String password;

    @Value("${mailisssl}")
    public void setIsSSL(String mailisssl) {
        EmailUtils.isSSL = Boolean.valueOf(mailisssl);
    }

    private static boolean isSSL;

    @Value("${mailsendcharset}")
    public void setCharse(String mailsendcharset) {
        EmailUtils.charse = mailsendcharset;
    }

    private static String charse;

    @Value("${mailsmtptimeout}")
    public void setTimeout(String mailsmtptimeout) {
        EmailUtils.timeout = mailsmtptimeout;
    }

    private static String timeout;


    /**
     * 发送邮件
     *
     * @param subject     主题
     * @param toUsers     收件人
     * @param ccUsers     抄送
     * @param content     邮件内容
     * @param attachfiles 附件列表  List<Map<String, String>> key: name && file
     */
    public static boolean sendEmail(String subject, String[] toUsers, String[] ccUsers, String content, List<Map<String, String>> attachfiles) {
        boolean flag = true;
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(host);
            javaMailSender.setUsername(authName);
            javaMailSender.setPassword(password);
            javaMailSender.setDefaultEncoding(charse);
            javaMailSender.setProtocol(protocol);
            javaMailSender.setPort(port);

            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", auth);
            properties.setProperty("mail.smtp.timeout", timeout);
            if (isSSL) {
                MailSSLSocketFactory sf = null;
                try {
                    sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true);
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.ssl.socketFactory", sf);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
            javaMailSender.setJavaMailProperties(properties);

            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setTo(toUsers);
            if (ccUsers != null && ccUsers.length > 0) {
                messageHelper.setCc(ccUsers);
            }
            messageHelper.setFrom(authName);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            if (attachfiles != null && attachfiles.size() > 0) {
                for (Map<String, String> attachfile : attachfiles) {
                    String attachfileName = attachfile.get("name");
                    File file = new File(attachfile.get("file"));
                    messageHelper.addAttachment(attachfileName, file);
                }
            }
            javaMailSender.send(mailMessage);

        } catch (Exception e) {
            logger.error("发送邮件失败!", e);
            flag = false;
        }
        return flag;
    }
}




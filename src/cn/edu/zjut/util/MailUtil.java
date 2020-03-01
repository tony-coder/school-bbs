package cn.edu.zjut.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.UnknownHostException;
import java.util.Properties;

public class MailUtil {
    //配置信息
    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";//邮件的传输协议
    private static final String MAIL_TRANSPORT_PROTOCOL_VALUE = "smtp";//使用smtp协议
    private static final String MAIL_HOST = "mail.host";//发送邮件的主机
    private static final String MAIL_HOST_VALUE = "smtp.163.com"; //发送邮件的服务器地址
    private static final String MAIL_DEBUG = "mail.debug"; //调试模式
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";//邮件smtp作者确认
    private static final String CONFIRM = "true";//确认
    private static final String SEND_ENCODING_LAYOUT = "text/html;charset=utf-8";//发送邮件的编码格式

    //邮件编辑信息(仅需写上自己的)
    private static final String MAIL_FROM = "zjutbbs@163.com";//邮件发送人
    private static final String MAIL_FROM_PASSWORD = "zjut163";//邮件发送人授权码
    private static final String MAIL_SUBJECT_REGISTER = "ZJUTBBS校园论坛【注册链接】";//邮件主题(注册链接)
    //    private static final String MAIL_SUBJECT_VERIFY_CODE = "testcode【邮箱验证码】";//邮件主题(邮箱验证码)
    private static final String MAIL_ORGANIZATION = "bfx";//邮件组织
//    private static final String MAIL_WEBSITE = "https://www.xxxx.com/";//组织主页
//    private static final String MAIL_ORGANIZATION_LOGO = "https://wwwxxxx.com/xxxx/xxxlogo.jpg";//网站logo
//    private static final String MAIL_ORGANIZATION_QRCODE = "https://www.xxxx.com/xxxx/xxx.jpg";//网站二维码图片

    private static final String HOST_IP = "localhost";
    private static final String PROJECT_NAME = "school_bbs";
    private static final String PORT = "8080";

    /**
     * 发送邮件的核心方法，邮件发送失败会抛出相应的异常，调用者应该捕捉这些异常
     *
     * @param to   邮件的发送对象
     * @param code 验证码
     * @throws javax.mail.MessagingException
     */
    public void sendEmail(String to, String code) throws Exception {
        //第一步：创建propeties
//        Properties props = new Properties();
//        props.put("mail.host", mailHost);       //存储发送邮件服务器的信息
//        props.put("mail.smtp.auth", "true");    //同时通过验证

        Properties props = new Properties();//key value:配置参数。真正发送邮件时再配置
        props.setProperty(MAIL_TRANSPORT_PROTOCOL, MAIL_TRANSPORT_PROTOCOL_VALUE);//指定邮件发送的协议，参数是规范规定的
        props.setProperty(MAIL_HOST, MAIL_HOST_VALUE);//指定发件服务器的地址，参数是规范规定的
//        props.setProperty(MAIL_DEBUG, "true");//邮件发送的调试模式，参数是规范规定的
        props.setProperty(MAIL_SMTP_AUTH, CONFIRM);//请求服务器进行身份认证。参数与具体的JavaMail实现有关
        Session session = Session.getInstance(props);//发送邮件时使用的环境配置
//        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        //设置邮件的头
        message.setFrom(new InternetAddress(MAIL_FROM));        //谁发送的
        message.setRecipients(Message.RecipientType.TO, to);    //发送给谁
        message.setSubject(MAIL_SUBJECT_REGISTER);
        //设置正文
//        message.setContent(content, SEND_ENCODING_LAYOUT);
        String info = "欢迎注册我们的校园论坛，点击下方链接激活您的账户<br/><br/>";
        //获取服务器地址
        String warning = "<br/><br/>本邮件为系统邮件，请勿回复";

        message.setContent(info + "http://" + HOST_IP + ":" + PORT + "/" + PROJECT_NAME + "/active.action?activeCode=" + code + warning, "text/html;charset=utf-8");
//        message.setText("<h1>hello</h1>");//纯文本
        message.saveChanges();

        //发送邮件
        Transport ts = session.getTransport();
        ts.connect(MAIL_FROM, MAIL_FROM_PASSWORD);          // 密码为授权码不是邮箱的登录密码
        ts.sendMessage(message, message.getAllRecipients());//对象，用实例方法}

    }

}

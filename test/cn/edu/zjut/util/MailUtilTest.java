package cn.edu.zjut.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class MailUtilTest {

    @Test
    public void sendEmail() {
        MailUtil mailUtil = new MailUtil();
        try {
            mailUtil.sendEmail("1606771552@qq.com", "123");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
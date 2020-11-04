package com.example.demo.Test;

import com.example.demo.Services.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharpterTest {
    @Autowired
    SendEmailService sendEmailService;
    @Test
    public void setSendEmailService(){
        String to="1679569188@qq.com";
        String subject="springboot发送邮件";
        String text="测试成功";
        String path="D:\\360downloads\\317701.jpg";
        try {
            sendEmailService.setMailSender(to,subject,text,path,"abf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

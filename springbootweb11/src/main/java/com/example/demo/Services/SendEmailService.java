package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    public void setMailSender(String to,String subject,String text,String path,String id)throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        //收件人
        helper.setTo(to);
        //标题
        helper.setSubject(subject);
        //true指的是html邮件，false指的是普通文本
        String content = "<html><body>" + text + "<img src=\'cid:" + id + "\'></img>" + "</body></html>";

        helper.setText(content, true);

        //添加图片
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addInline(id,file);



        try{
            mailSender.send(message);
        }catch (MailException e){
            System.out.println("邮件发送失败");
            e.printStackTrace();
        }



    }


}

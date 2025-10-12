package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
     private JavaMailSender javaMailSender;
     
     public void sendMail(String to,String subject,String body){
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);
     }
}

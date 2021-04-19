package com.portfolio.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void mailSend(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("myeongkuk0406@gmail.com");
        message.setSubject("Email Certification");
        message.setText("인증코드 : " + (int)(Math.random()*1000000));
        mailSender.send(message);
    }
}

package com.portfolio.board.service;

import com.portfolio.board.domain.Member;
import com.portfolio.board.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;

    @Autowired
    public MailService(JavaMailSender mailSender, MailRepository mailRepository) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
    }

    public void mailSend(String email, Integer number){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("myeongkuk0406@gmail.com");
        message.setSubject("Email Certification");
        message.setText("인증코드 : " + number);
        mailSender.send(message);
    }

    @Transactional
    public Integer mailInfoSave(Member member){
        return mailRepository.saveMailInfo(member);
    }
}

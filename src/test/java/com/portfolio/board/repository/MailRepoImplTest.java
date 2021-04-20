package com.portfolio.board.repository;

import com.portfolio.board.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailRepoImplTest {

    @Autowired
    MailRepository mailRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void findTest(){
        Mail byMemberId = mailRepository.findByMemberId(1L);
        System.out.println("byMemberId = " + byMemberId);
    }


}
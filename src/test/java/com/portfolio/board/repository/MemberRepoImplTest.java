package com.portfolio.board.repository;

import com.portfolio.board.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepoImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTest(){
        Member member = new Member();
        member.setName("admin");
        member.setEmail("");
        member.setPassword("qweqwe1");
        member.setAuth(true);
        member.setMailCertified(true);
        Long saveId = memberRepository.save(member);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void findTest(){
        Long byEmail = memberRepository.findByEmail("myeongkuk@naver.com");
    }
}
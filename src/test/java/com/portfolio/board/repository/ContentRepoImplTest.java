package com.portfolio.board.repository;

import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.ContentStatus;
import com.portfolio.board.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContentRepoImplTest {

    @Autowired
    ContentRepository contentRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTest(){
        Content content = new Content();
        content.setText("akakakasdlasdnlqndwlqwelq");
        contentRepository.save(content);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void deleteTest(){
        Content content = new Content();
        contentRepository.delete(16L);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void test(){
        ContentStatus contentStatus = new ContentStatus();
        Content byId = contentRepository.findById(4L);
        Member member = memberRepository.findById(2L);
        contentStatus.setContent(byId);
        contentStatus.setMember(member);
        contentStatus.setDeleted(false);
        contentRepository.saveGoodCount(contentStatus);
        Assertions.assertThat(contentRepository.viewGoodCount(4L)).isEqualTo(6);
    }


}
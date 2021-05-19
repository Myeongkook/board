package com.portfolio.board.repository;

import com.portfolio.board.domain.Content;
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
public class ContentRepoImplTest {

    @Autowired
    ContentRepository contentRepository;

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

}
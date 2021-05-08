package com.portfolio.board.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaltingPw {

    @Test
    public void sample(){
        String abcd = BCrypt.hashpw("abcd", BCrypt.gensalt());
        Assertions.assertThat(BCrypt.checkpw("abcd",abcd)).isTrue();
        Assertions.assertThat(BCrypt.checkpw("abce",abcd)).isFalse();
    }
}

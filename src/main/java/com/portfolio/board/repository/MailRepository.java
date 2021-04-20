package com.portfolio.board.repository;

import com.portfolio.board.domain.Mail;
import com.portfolio.board.domain.Member;

public interface MailRepository {
    Integer saveMailInfo(Member member);
    Mail findByMemberId(Long id);
}

package com.portfolio.board.service;

import com.portfolio.board.domain.Member;

public interface MemberService {

    Long save(Member member);
    int login(Member member);
    boolean mailCertification(String email, Integer number);
    Member findById(Long id);
    Long findByEmail(String email);
    boolean existEmail(String email);
    boolean existName(String name);
}

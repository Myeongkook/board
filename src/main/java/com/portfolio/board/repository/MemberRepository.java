package com.portfolio.board.repository;

import com.portfolio.board.domain.Member;

public interface MemberRepository {
    Long save(Member member);
    Member findById(Long id);
    Long findByEmail(String email);
    boolean checkByCertified(Member member);
}

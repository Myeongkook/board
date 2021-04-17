package com.portfolio.board.service;

import com.portfolio.board.domain.Member;

public interface MemberService {

    Long save(Member member);
    int login(Member member);
}

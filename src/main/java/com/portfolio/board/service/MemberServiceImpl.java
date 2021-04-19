package com.portfolio.board.service;

import com.portfolio.board.domain.Member;
import com.portfolio.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public int login(Member member) {
        Long byEmail = memberRepository.findByEmail(member.getEmail());
        if (byEmail == 0L){
            return 0; // login 실패
        }
        Member byId = memberRepository.findById(byEmail);
        if(byId.getPassword().equals(member.getPassword())){
            if(memberRepository.checkByCertified(memberRepository.findById(memberRepository.findByEmail(member.getEmail())))){
                return 2; // login 성공
            }return 1; // login 실패 - 메인인증 필요
        }return 0; // login 실패
    }

}

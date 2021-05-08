package com.portfolio.board.service;

import com.portfolio.board.domain.Mail;
import com.portfolio.board.domain.Member;
import com.portfolio.board.repository.MailRepository;
import com.portfolio.board.repository.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MailRepository mailRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MailRepository mailRepository) {
        this.memberRepository = memberRepository;
        this.mailRepository = mailRepository;
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
        if(BCrypt.checkpw(member.getPassword(),byId.getPassword())){
            if(memberRepository.checkByCertified(memberRepository.findById(memberRepository.findByEmail(member.getEmail())))){
                return 2; // login 성공
            }return 1; // login 실패 - 메인인증 필요
        }return 0; // login 실패
    }

    @Override
    @Transactional
    public boolean mailCertification(String email, Integer number) {
        if(mailRepository.findByMemberId(memberRepository.findByEmail(email)).getNumber().equals(number)){
            memberRepository.certified(memberRepository.findById(memberRepository.findByEmail(email)));
            return true;
        }
        return false;
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Long findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public boolean existEmail(String email) {
        return memberRepository.findByEmail(email) > 0L;
    }

}

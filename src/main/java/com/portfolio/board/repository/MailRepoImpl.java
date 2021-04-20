package com.portfolio.board.repository;

import com.portfolio.board.domain.Mail;
import com.portfolio.board.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MailRepoImpl implements MailRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Integer saveMailInfo(Member member) {
        Mail mail = new Mail();
        mail.setMember(member);
        mail.setNumber((int)(Math.random()*1000000));
        em.persist(mail);
        return mail.getNumber();
    }

    @Override
    public Mail findByMemberId(Long id) {
        return em.createQuery("select m from Mail m where m.member.id=:id",Mail.class)
                .setParameter("id",id)
                .getSingleResult();
    }
}

package com.portfolio.board.repository;

import com.portfolio.board.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepoImpl implements MemberRepository{

    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    @Override
    public Long findByEmail(String email) {
        try{
            return em.createQuery("select m from Member m where m.email = :email", Member.class)
                        .setParameter("email",email)
                        .getSingleResult()
                        .getId();
        }catch (NoResultException e){
            return 0L;
        }
    }

    @Override
    public boolean checkByCertified(Member member) {
        try {
            em.createQuery("select m from Member m where m.id =:id and m.mailCertified = true ")
                    .setParameter("id", member.getId())
                    .getSingleResult();
            return true;
        }catch (NoResultException e){
            return false;
        }
    }

    @Override
    public void certified(Member member) {
        member.setMailCertified(true);
    }


}

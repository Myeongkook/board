package com.portfolio.board.repository;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContentRepoImpl implements ContentRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(Content content) {
        em.persist(content);
        return content.getId();
    }

    @Override
    public List<Content> viewAllContent() {
        return em.createQuery("select c from Content c", Content.class).getResultList();
    }

    @Override
    public Content findById(Long id) {
        return em.find(Content.class, id);
    }

    @Override
    public boolean delete(Long id) {
        try{
            em.remove(em.find(Content.class, id));
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public void countHit(Long id) {
        Content content = em.find(Content.class, id);
        int hitCount = content.getHitCount();
        hitCount += 1;
        content.setHitCount(hitCount);
    }

    @Override
    public void saveComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    public List<Comment> viewAllComment(Long id) {
        return em.createQuery("select c from Comment c where c.content = :content",Comment.class)
                .setParameter("content",em.find(Content.class,id))
                .getResultList();
    }
}

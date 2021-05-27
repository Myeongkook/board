package com.portfolio.board.repository;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.ContentStatus;
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
            List<Comment> commentList = em.createQuery("select c from Comment c where c.content =:id", Comment.class)
                    .setParameter("id", em.find(Content.class, id))
                    .getResultList();
            List<ContentStatus> contentStatusList = em.createQuery("select s from ContentStatus s where s.content = :id",ContentStatus.class)
                    .setParameter("id",em.find(Content.class,id))
                    .getResultList();
            for (Comment comment : commentList) {
                em.remove(comment);
            }
            for (ContentStatus contentStatus : contentStatusList) {
                em.remove(contentStatus);
            }
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

    @Override
    public Long viewGoodCount(Long id) {
        return em.createQuery("select count(c) from ContentStatus c where c.content = :id", Long.class)
                .setParameter("id", em.find(Content.class,id))
                .getSingleResult();
    }

    @Override
    public Long saveGoodCount(ContentStatus contentStatus) {
        Long result = em.createQuery("select count(c) from ContentStatus c where c.content=:content and c.member = :member", Long.class)
                .setParameter("content",contentStatus.getContent())
                .setParameter("member",contentStatus.getMember())
                .getSingleResult();
        if (result == 0) {
            em.persist(contentStatus);
            return contentStatus.getId();
        }
        return 0L;
    }
}

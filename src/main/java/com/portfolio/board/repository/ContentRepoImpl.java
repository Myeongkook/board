package com.portfolio.board.repository;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.ContentStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        return em.createQuery("select c from Content c where c.deleted = false ", Content.class).getResultList();
    }

    @Override
    public Content findById(Long id) {
        return em.find(Content.class, id);
    }

    @Override
    public void delete(Long id) {
            em.find(Content.class, id).setDeleted(true);
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
        return em.createQuery("select c from Comment c where c.content = :content and c.deleted = false",Comment.class)
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

    @Override
    public Long deleteComment(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
        return comment.getContent().getId();
    }

    @Override
    public Long findStatusByCommentAndMember(ContentStatus contentStatus) {
        try{
            ContentStatus singleResult = em.createQuery("select c from ContentStatus c where c.content = :content and c.member = : member", ContentStatus.class)
                    .setParameter("content", contentStatus.getContent())
                    .setParameter("member", contentStatus.getMember())
                    .getSingleResult();
            return singleResult.getId();
        }catch (NoResultException e){
            return 0L;
        }
    }

    @Override
    public void deleteStatus(Long id) {
        ContentStatus contentStatus = em.find(ContentStatus.class, id);
        em.remove(contentStatus);
    }
}

package com.portfolio.board.repository;

import com.portfolio.board.domain.Content;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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
}

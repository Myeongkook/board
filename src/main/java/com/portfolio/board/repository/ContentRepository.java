package com.portfolio.board.repository;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;

import java.util.List;

public interface ContentRepository {
    Long save(Content content);
    List<Content> viewAllContent();
    Content findById(Long id);
    boolean delete(Long id);
    void countHit(Long id);
    void saveComment(Comment comment);
    List<Comment> viewAllComment(Long id);
}

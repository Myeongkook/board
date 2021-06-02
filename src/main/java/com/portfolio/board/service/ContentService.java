package com.portfolio.board.service;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.ContentStatus;

import java.util.List;

public interface ContentService {
    void saveContent(Content content);
    List<Content> viewAllContent();
    Content readContent(Long id);
    void deleteContent(Long id);
    void saveComment(Comment comment);
    List<Comment> ViewAllComment(Long id);
    void CountingGood(ContentStatus contentStatus);
    Long ViewContentGood(Long id);
    Long findByCommentIdAndDeleteComment(Long id);
    void CountingHit(Long id);
    void modifyContent(Content content);
}

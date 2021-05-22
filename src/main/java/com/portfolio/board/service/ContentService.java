package com.portfolio.board.service;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;

import java.util.List;

public interface ContentService {
    void saveContent(Content content);
    List<Content> viewAllContent();
    Content readContent(Long id);
    Boolean deleteContent(Long id);
    void saveComment(Comment comment);
    List<Comment> ViewAllComment(Long id);
}

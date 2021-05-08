package com.portfolio.board.service;

import com.portfolio.board.domain.Content;

import java.util.List;

public interface ContentService {
    void saveContent(Content content);
    List<Content> viewAllContent();
}

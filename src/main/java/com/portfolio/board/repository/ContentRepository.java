package com.portfolio.board.repository;

import com.portfolio.board.domain.Content;

public interface ContentRepository {
    Long save(Content content);
}

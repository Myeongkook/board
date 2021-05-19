package com.portfolio.board.service;

import com.portfolio.board.domain.Content;
import com.portfolio.board.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{

    private final ContentRepository contentRepository;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    @Transactional
    public void saveContent(Content content) {
        contentRepository.save(content);
    }

    @Override
    @Transactional
    public List<Content> viewAllContent() {
        return contentRepository.viewAllContent();
    }

    @Override
    public Content readContent(Long id) {
        return contentRepository.findById(id);
    }

    @Override
    @Transactional
    public Boolean deleteContent(Long id) {
        return contentRepository.delete(id);
    }
}

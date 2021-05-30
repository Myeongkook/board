package com.portfolio.board.service;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.ContentStatus;
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
    @Transactional
    public Content readContent(Long id) {
        return contentRepository.findById(id);
    }


    @Override
    @Transactional
    public void deleteContent(Long id) {
        contentRepository.delete(id);
    }

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        contentRepository.saveComment(comment);
    }

    @Override
    public List<Comment> ViewAllComment(Long id) {
        return contentRepository.viewAllComment(id);
    }

    @Override
    @Transactional
    public void CountingGood(ContentStatus contentStatus) {
        contentRepository.saveGoodCount(contentStatus);
    }

    @Override
    public Long ViewContentGood(Long id) {
        return contentRepository.viewGoodCount(id);
    }

    @Override
    @Transactional
    public Long findByCommentIdAndDeleteComment(Long id) {
        return contentRepository.deleteComment(id);
    }

    @Override
    @Transactional
    public void CountingHit(Long id) {
        contentRepository.countHit(id);
    }
}

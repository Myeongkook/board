package com.portfolio.board.controller;

import com.portfolio.board.domain.Comment;
import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.ContentStatus;
import com.portfolio.board.domain.Member;
import com.portfolio.board.repository.MemberRepository;
import com.portfolio.board.service.ContentService;
import com.portfolio.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(){
        return "write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(Content content, HttpSession httpSession){
        Object member = httpSession.getAttribute("member");
        Member writer = (Member) member;
        content.setMember(writer);
        contentService.saveContent(content);
        return "redirect:/home";
    }

    @RequestMapping(value = "read/{content_id}", method = RequestMethod.GET)
    public ModelAndView read(@PathVariable Long content_id){
        Content content = contentService.readContent(content_id);
        List<Comment> comments = contentService.ViewAllComment(content_id);
        ModelAndView m = new ModelAndView();
        Long goodCount = contentService.ViewContentGood(content_id);
        m.addObject("good", goodCount);
        m.addObject("content", content);
        m.addObject("comments",comments);
        m.setViewName("contents");
        return m;
    }

    @RequestMapping(value = "/delete/{content_id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long content_id){
        contentService.deleteContent(content_id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/comment/{content_id}", method = RequestMethod.POST)
    public String writeComment(@PathVariable Long content_id, Comment comment, HttpSession httpSession){
        comment.setContent(contentService.readContent(content_id));
        comment.setMember((Member)httpSession.getAttribute("member"));
        contentService.saveComment(comment);
        return "redirect:/read/" + content_id;
    }

    @RequestMapping(value = "read/good/{content_id}", method = RequestMethod.GET)
    public String addGood(@PathVariable Long content_id, HttpSession httpSession){
        ContentStatus c = new ContentStatus();
        c.setContent(contentService.readContent(content_id));
        c.setMember((Member) httpSession.getAttribute("member"));
        c.setDeleted(false);
        contentService.CountingGood(c);
        return "redirect:/read/" + content_id;
    }
}

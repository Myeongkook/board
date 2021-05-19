package com.portfolio.board.controller;

import com.portfolio.board.domain.Content;
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
        ModelAndView m = new ModelAndView();
        m.addObject("content", content);
        m.setViewName("contents");
        return m;
    }

    @RequestMapping(value = "/delete/{content_id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long content_id){
        contentService.deleteContent(content_id);
        return "redirect:/home";
    }
}

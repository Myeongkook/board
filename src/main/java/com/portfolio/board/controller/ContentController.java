package com.portfolio.board.controller;

import com.portfolio.board.domain.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContentController {

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(){
        return "write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(Content content){

        return "write";
    }
}

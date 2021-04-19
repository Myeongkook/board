package com.portfolio.board.controller;

import com.portfolio.board.domain.Member;
import com.portfolio.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Login(){
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String Login(Member member){
        if(member != null){
            if(memberService.login(member) == 0){
                return "index";
            }else if(memberService.login(member) == 1){
                return "mailAuth";
            }else {
                return "home";
            }
        }
        return "index";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String Signup(){
        return "signup";
    }
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String Signup(Member member){
        memberService.save(member);
        // mailService.sendCertificate
        return "redirect:/index";
    }
}

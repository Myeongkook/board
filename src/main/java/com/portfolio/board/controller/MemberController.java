package com.portfolio.board.controller;

import com.portfolio.board.domain.Mail;
import com.portfolio.board.domain.Member;
import com.portfolio.board.service.MailService;
import com.portfolio.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    @Autowired
    public MemberController(MemberService memberService, MailService mailService) {
        this.memberService = memberService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Login(){
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String Login(Member member, HttpSession httpSession, Model model){
        if(member != null){
            if(memberService.login(member) == 0){
                return "index";
            }else if(memberService.login(member) == 1){
                httpSession.setAttribute("mail", member.getEmail());
                model.addAttribute("nick",httpSession.getAttribute("mail"));
                return "mailAuth";
            }else {
                httpSession.setAttribute("mail", member.getEmail());
                model.addAttribute("nick",httpSession.getAttribute("mail"));
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
        if(memberService.save(member) > 0L){
            mailService.mailSend(member.getEmail(),mailService.mailInfoSave(member));
            return "redirect:/";
        }
        return "signup";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String Auth(HttpSession httpSession, Mail mail){
        if(memberService.mailCertification(String.valueOf(httpSession.getAttribute("mail")), mail.getNumber())){
            System.out.println("성공하긴하니");
            return "home";
        }
        return "signup";
    }
}

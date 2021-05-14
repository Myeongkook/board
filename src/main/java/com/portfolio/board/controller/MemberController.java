package com.portfolio.board.controller;

import com.portfolio.board.domain.Content;
import com.portfolio.board.domain.Mail;
import com.portfolio.board.domain.Member;
import com.portfolio.board.service.ContentService;
import com.portfolio.board.service.MailService;
import com.portfolio.board.service.MemberService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private final ContentService contentService;

    @Autowired
    public MemberController(MemberService memberService, MailService mailService, ContentService contentService) {
        this.memberService = memberService;
        this.mailService = mailService;
        this.contentService = contentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Login(HttpSession httpSession){
        if (httpSession.getAttribute("member") != null){
            return "redirect:/home";
        }
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
                httpSession.setAttribute("member", memberService.findById(memberService.findByEmail(member.getEmail())));
                return "redirect:/home";
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
        member.setPassword(BCrypt.hashpw(member.getPassword(),BCrypt.gensalt()));
        if(memberService.save(member) > 0L){
            mailService.mailSend(member.getEmail(),mailService.mailInfoSave(member));
            return "redirect:";
        }
        return "signup";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String Auth(HttpSession httpSession, Mail mail){
        if(memberService.mailCertification(String.valueOf(httpSession.getAttribute("mail")), mail.getNumber())){
            Object sessionEmail = httpSession.getAttribute("mail");
            String castingEmail = (String) sessionEmail;
            httpSession.setAttribute("member", memberService.findById(memberService.findByEmail(castingEmail)));
            return "redirect:/home";
        }
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Home(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("member") == null){
            return "redirect:";
        }
        List<Content> list = contentService.viewAllContent();
        model.addAttribute("list", list);
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String Logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:";
    }

    @GetMapping("/mail-check/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(memberService.existEmail(email));
    }

    @GetMapping("/name-check/{name}")
    public ResponseEntity<Boolean> checkNameDuplicate(@PathVariable String name){
        return ResponseEntity.ok(memberService.existName(name));
    }

    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }
}

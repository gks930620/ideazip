package jpabook.jpashop.controller;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.LoginSuccessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    LoginSuccessValidator loginSuccessValidator;

    @GetMapping("/login/login")
    public String login(){
        return "login/login";
    }



}

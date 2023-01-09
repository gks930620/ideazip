package jpabook.jpashop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //private final


    @GetMapping("/member/join")
    public String memberJoin(){   // 굳이 이메일인증같은거 필요없다.

    }

}

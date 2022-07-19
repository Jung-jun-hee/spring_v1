package com.example.spring.controller;

import com.example.spring.model.UserVo;
import com.example.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    //private final MemberService memberService = new MemberService();
    // 이렇게 쓰면 다른 컨트롤러에서도 서비스를 가져다 쓸 수 있기 때문에 하나만 생성해서 같이 공유하는게 가장 좋다.
    // 그래서 스프링 컨테이너에 등록시켜 사용하면 좋다.

    // 서비스를 스프링이 스프링 컨테이너에 있는 서비스를 가져다가 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        UserVo user = new UserVo();
        user.setName(form.getName());
        System.out.println(user.getName());
         memberService.join(user);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<UserVo> users = memberService.findUsers();
        model.addAttribute("members", users);
        return "members/memberList";
    }
}

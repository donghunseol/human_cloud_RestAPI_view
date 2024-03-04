package com.example.project1.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    @GetMapping("/")
    public String index(HttpServletRequest request){
        request.setAttribute("isScrap", true);

        return "index";
    }

    @PostMapping("/user/loginForm")
    public String login(){
        return "user/loginForm";
    }

    @PostMapping("/user/joinForm")
    public String join(){
        return "user/joinForm";
    }

    @GetMapping("/user/updateForm")
    public String update(){
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "myPage/main";
    }

    @GetMapping("/myPage/selectList")
    public String myPageList(){
        return "myPage/selectList";
    }
}

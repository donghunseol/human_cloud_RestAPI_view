package com.example.project1.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user/loginForm")
    public String login() {
        return "/user/loginForm";
    }

    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/user/login")
    public String login( UserRequest.LoginDTO requestDTO) {

        User user = userRepository.findByUsernameAndPassword(requestDTO);
        System.out.println(user);

        session.setAttribute("sessionUser" , user );
        System.out.println(user);

        

        return "redirect:/";
    }


    @PostMapping("/user/join")
    public String join(UserRequest.JoinDTO requestDTO){
        userRepository.save(requestDTO);

       // HttpSession s =request.getSession();
//        System.out.println("정보 : " + requestDTO);

        return "redirect:/user/loginForm";
   }


    @GetMapping("/user/updateForm")
    public String update() {
        return "user/updateForm";
    }

    @PostMapping("/user/updateForm")
    public String update(UserRequest.JoinDTO requestDTO) {
        return "/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "myPage/main";
    }

    @GetMapping("/myPage/selectList")
    public String myPageList() {
        return "myPage/selectList";
    }
}

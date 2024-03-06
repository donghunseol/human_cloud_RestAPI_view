package com.example.project1.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //업데이트 창 (사용자 정보 담기 전,)

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
    //업데이트 (사용자 정보 담긴 update4)
    @PostMapping("/user/updateForm")
    public String updateForm (@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser ==null){
            return "redirect/loginForm";}

        return "user/updateForm";
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

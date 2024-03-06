package com.example.project1.user;

import com.example.project1.resume.ResumeRepository;
import com.example.project1.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<ResumeResponse.DTO> resumeList = resumeRepository.findAll();
        request.setAttribute("resumeList", resumeList);

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

    @PostMapping("/user/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        System.out.println("정보 : " + requestDTO);

        userRepository.save(requestDTO);

        System.out.println(requestDTO.getRole());

        // repo db연결

        return "redirect:/user/loginForm";
    }


    @GetMapping("/user/updateForm")
    public String update() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
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

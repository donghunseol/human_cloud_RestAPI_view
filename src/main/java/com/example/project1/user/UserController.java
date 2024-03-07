package com.example.project1.user;

import com.example.project1._core.util.ApiUtil;
import com.example.project1.apply.ApplyRepository;
import com.example.project1.apply.ApplyRequest;
import com.example.project1.apply.ApplyResponse;
import com.example.project1.board.BoardResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.swing.interop.SwingInterOpUtils;
import com.example.project1.resume.ResumeRepository;
import com.example.project1.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;
    private final ResumeRepository resumeRepository;
    private final ApplyRepository applyRepository;

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

    @PostMapping("/user/login")
    public String login( UserRequest.LoginDTO requestDTO) {
        User user = userRepository.findByUsernameAndPassword(requestDTO);
        System.out.println(user);

        session.setAttribute("sessionUser" , user);
        System.out.println(user);

        return "redirect:/";
    }

//    @GetMapping("/board/{id}")
//    public String detail(@PathVariable int id, HttpServletRequest request) {
//        // 1. 모델 진입 - 상세보기 데이터 가져오기
//        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);
//
//        // 2. 페이지 주인 여부 체크 (board의 userId와 sessionUser의 id를 비교)
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        boolean pageOwner;
//        if(sessionUser == null){
//            pageOwner = false;
//        }else{
//            int 게시글작성자번호 = responseDTO.getUserId();
//            int 로그인한사람의번호 = sessionUser.getId();
//            pageOwner = 게시글작성자번호 == 로그인한사람의번호;
//        }
//
//        request.setAttribute("board", responseDTO);
//        request.setAttribute("pageOwner", pageOwner);
//        return "board/detail";
//    }



    @PostMapping("/user/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        userRepository.save(requestDTO);
        System.out.println(requestDTO);

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
    @PostMapping("/user/{id}/updateForm")
    public String update (@PathVariable Integer  id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        if(sessionUser == null){
            return "redirect:/loginForm";
        }

        return "myPage/main";
    }

    @GetMapping("/user/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest request) {
        List<ResumeResponse.DTO> myResumeList = resumeRepository.findAllByUserId(1);
        request.setAttribute("myResumeList", myResumeList);
        return "myPage/main";
    }

    @GetMapping("/myPage/selectList")
    public String myPageList() {
        User user = (User) session.getAttribute("sessionUser");
        System.out.println("user 정보 : " + user);
        List<ApplyResponse.UserListDTO> applyList = applyRepository.findUserApplyById(user.getId());
        session.setAttribute("applyList", applyList);
        System.out.println("지원한 공고 : " + applyList);

        return "/myPage/selectList";
    }

//    @PostMapping("/user/{id}/delete")
//    public String DeleteAll(UserRequest.DeleteDTO requestDTO){
//        List<UserRequest.DeleteDTO> myDeleteList = userRepository.findAllWithDelete()
//    }

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            return new ApiUtil<>(true);
        }else{
            return new ApiUtil<>(false);
        }
    }
}



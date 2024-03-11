package com.example.project1.user;

import com.example.project1._core.util.ApiUtil;
import com.example.project1.apply.ApplyRepository;
import com.example.project1.apply.ApplyResponse;
import com.example.project1.notice.NoticeRepository;
import com.example.project1.notice.NoticeResponse;
import com.example.project1.resume.ResumeRepository;
import com.example.project1.resume.ResumeResponse;
import com.example.project1.scrap.ScrapRepository;
import com.example.project1.scrap.ScrapResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;
    private final ResumeRepository resumeRepository;
    private final ApplyRepository applyRepository;
    private final ScrapRepository scrapRepository;
    private final NoticeRepository noticeRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "") String keyword) {
        User user = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.DTO> resumeList = new ArrayList<>();
        List<NoticeResponse.DTO> noticeList = new ArrayList<>();

        if (user != null) {
            if (user.getRole() == 0) { // 개인 로그인
                if (keyword.isBlank()) {
                    noticeList = noticeRepository.findAll();
                } else {
                    noticeList = noticeRepository.findSearchAll(keyword);
                }
            } else if (user.getRole() == 1) { // 기업 로그인
                if (keyword.isBlank()) {
                    resumeList = resumeRepository.findAll();
                } else {
                    resumeList = resumeRepository.findSearchAll(keyword);
                }
            }
        }
        if (user == null) {
            if (keyword.isBlank()) {
                noticeList = noticeRepository.findAll();
            } else {
                noticeList = noticeRepository.findSearchAll(keyword);
            }
        }
        System.out.println(noticeList);

        request.setAttribute("resumeList", resumeList);
        request.setAttribute("noticeList", noticeList);

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
    public String login(UserRequest.LoginDTO requestDTO) {
        User user = userRepository.findByUsernameAndPassword(requestDTO);
        session.setAttribute("sessionUser", user);

        // false 는 개인 true 는 기업
        Boolean isLogin = false;
        if (user.getRole() == 1) {
            isLogin = true;
        } else {
            isLogin = false;
        }
        session.setAttribute("isLogin", isLogin);


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
        return "/user/updateForm";
    }

    //업데이트 (사용자 정보 담긴 update4)
    @PostMapping("/user/{id}/updateForm")
    public String update(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        return "/myPage/main";
    }

    @GetMapping("/user/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.DTO> myResumeList = resumeRepository.findAllByUserId(user.getId());
        request.setAttribute("myResumeList", myResumeList);
        return "myPage/main";
    }

    @GetMapping("/myPage/selectList")
    public String myPageList(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        System.out.println("user 정보 : " + user);
        List<ApplyResponse.UserListDTO> applyList = applyRepository.findUserApplyById(user.getId());
        request.setAttribute("applyList", applyList);
        System.out.println("지원한 공고 : " + applyList);

        return "/myPage/selectList";
    }

//    @PostMapping("/user/{id}/delete")
//    public String DeleteAll(UserRequest.DeleteDTO requestDTO){
//        List<UserRequest.DeleteDTO> myDeleteList = userRepository.findAllWithDelete()
//    }

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }
    }

    @GetMapping("/scrap/{id}")
    public String scrapList(@PathVariable Integer id) {
        User user = (User) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapDTO> scrapList = scrapRepository.findByIdList(id, user.getRole());
        System.out.println(scrapList);

        session.setAttribute("scrapList", scrapList);

        System.out.println(scrapList);

        return "/scrap/main";
    }
}



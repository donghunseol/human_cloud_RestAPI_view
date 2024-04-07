package com.example.project_v2.user;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.apply.Apply;
import com.example.project_v2.apply.ApplyResponse;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.scrap.ScrapResponse;
import com.example.project_v2.scrap.ScrapService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final ScrapService scrapService;
    private final HttpSession session;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;



    // 로그인
    @PostMapping("/users/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
        System.out.println("sessionUser = " + sessionUser);
        session.setAttribute("sessionUser", sessionUser);
        boolean isLoginUser = false;
        // true 면 기업, false 면 개인
        if (sessionUser.getRole() == 0) {
            isLoginUser = false;
        } else {
            isLoginUser = true;
        }
        session.setAttribute("isLoginUser", isLoginUser);
        return "redirect:/";
    }

    // 로그인 화면
    @GetMapping("/users/login-form")
    public String login() {
        return "/user/login-form";
    }

    // 회원 가입
    @PostMapping("/users/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        userService.join(reqDTO);
        return "redirect:/user/login-form";
    }

    // 회원가입(username) 중복 확인
    @GetMapping("/api/username-same-checks")
    public ApiUtil<?> usernameSameCheck(String username) {
        User user = userService.sameCheck(username);
        if (user == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }
    }

    // 회원 가입 페이지
    @GetMapping("/users/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    // 회원 정보 수정
    @PostMapping("/users/{id}/update")
    public String update(@PathVariable Integer id, UserRequest.UpdateDTO reqDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.update(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return "redirect:/myPages";
    }

    // 회원 정보 수정 화면
    @GetMapping("/users/{id}/update-form")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request) {

        //TODO: 서비스로 빼야함
        User user = userJPARepository.findById(id).get();
        request.setAttribute("user",user);
        return "user/update-form";
    }

    // 로그아웃
    @GetMapping("/users/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 메인 화면
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "id") String sortBy,
                        @RequestParam(defaultValue = "desc") String direction,
                        @RequestParam(required = false) String skillName,
                        HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        List<?> mainPageList;

        if (skillName != null && !skillName.isEmpty()) {
            mainPageList = userService.getMainPageByUserRoleAndSkill(sessionUser, skillName, pageable);
            if (sessionUser != null) {
                if (sessionUser.getRole() == 1) {
                    request.setAttribute("resumeList", mainPageList);
                } else {
                    request.setAttribute("noticeList", mainPageList);
                }
            } else {
                request.setAttribute("noticeList", mainPageList);
            }
        } else {
            mainPageList = userService.getMainPageByUserRole(sessionUser, pageable);
            if (sessionUser != null) {
                if (sessionUser.getRole() == 1) {
                    request.setAttribute("resumeList", mainPageList);
                } else {
                    request.setAttribute("noticeList", mainPageList);
                }
            } else {
                request.setAttribute("noticeList", mainPageList);
            }
        }
        return "index";
    }


    // 마이페이지 메인 (공고, 이력서 출력)
    @GetMapping("/myPages")
    public String myPage(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(defaultValue = "id") String sortBy,
                         @RequestParam(defaultValue = "desc") String direction,
                         HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        List<?> myPageList = userService.getMyPage(sessionUser, pageable);

        if (sessionUser != null) {
            if (sessionUser.getRole() == 1) {
                request.setAttribute("noticeList", myPageList);
            } else {
                request.setAttribute("resumeList", myPageList);
            }
        } else {
            return "user/login-form";
        }

        return "myPage/main";
    }

    // 스크랩 목록 확인
    @GetMapping("/scraps/{id}")
    public String scrapList(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "id") String sortBy,
                            @RequestParam(defaultValue = "desc") String direction,
                            @PathVariable Integer id,
                            HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        List<ScrapResponse.ScrapListDTO> respDTO = scrapService.scrapList(sessionUser.getId(), id, pageable);
        request.setAttribute("scrapList", respDTO);

        return "scrap/main";
    }

    // 마이 페이지 - 지원한 공고 (공고 출력 / 이력서 신청 여부)
    @GetMapping("/myPages/{id}/select-list")
    public String myPageList(@PathVariable("id") Integer userId, HttpServletRequest request) {// 사용자가 지원한 공고 정보 조회
        List<Apply> applies = userService.findAppliesByUserId(userId);

        // 응답 객체 구성
        List<ApplyResponse.UserListDTO> responseList = applies.stream()
                .map(apply -> new ApplyResponse.UserListDTO(apply))
                .collect(Collectors.toList());

        request.setAttribute("applyList", responseList);

        return "myPage/select-list";
    }
}



package com.example.project_v2.user;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // 로그인
    @PostMapping("/users/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        SessionUser sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", SessionUser.toEntity(sessionUser));
        return "redirect:/";
    }

    // 로그인 화면
    @GetMapping("/users/login-form")
    public String login() {
        return "/user/login-form";
    }

    // 메인 화면
    @GetMapping("/")
    public ResponseEntity<?> index(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy,
                                   @RequestParam(defaultValue = "desc") String direction,
                                   @RequestParam(required = false) String skillName) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        List<?> mainPageList;

        if (skillName != null && !skillName.isEmpty()) {
            mainPageList = userService.getMainPageByUserRoleAndSkill(sessionUser, skillName, pageable);
        } else {
            mainPageList = userService.getMainPageByUserRole(sessionUser, pageable);
        }

        return ResponseEntity.ok(new ApiUtil<>(mainPageList));
    }

    // 회원 가입
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.DTO respDTO = userService.join(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 회원 가입 페이지
    @GetMapping("/users/join-form")
    public String joinForm() {
        return "/user/join-form";
    }


    // 회원 정보 수정
    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.update(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    // 회원 정보 수정 화면
    @GetMapping("/api/users/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {
        return "/user/update-form";
    }


    // 로그아웃
    @GetMapping("/api/users/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 마이페이지 메인 (공고, 이력서 출력)
    @GetMapping("/api/myPages")
    public ResponseEntity<?> myPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy,
                                    @RequestParam(defaultValue = "desc") String direction) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        List<?> myPageList = userService.getMyPage(sessionUser, pageable);
        return ResponseEntity.ok(new ApiUtil<>(myPageList));
    }

    // 마이 페이지 - 지원한 공고 (공고 출력 / 이력서 신청 여부)
    @GetMapping("/api/myPages/selectList")
    public ResponseEntity<?> myPageList() {
        return ResponseEntity.ok(new ApiUtil<>(null));
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

    // 스크랩 여부 확인
    @GetMapping("/api/scraps/{id}")
    public ResponseEntity<?> scrapList(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}



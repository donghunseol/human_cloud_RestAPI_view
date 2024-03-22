package com.example.project_v2.user;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserJPARepository userJPARepository;
    private final HttpSession session;

    // 메인 화면
    @GetMapping("/index")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원 가입
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        User user = userService.join(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    // 로그인
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원 정보 수정
    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 로그아웃
    @GetMapping("/api/users/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 마이페이지 메인 (공고, 이력서 출력)
    @GetMapping("/api/myPages")
    public ResponseEntity<?> myPage() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 마이 페이지 - 지원한 공고 (공고 출력 / 이력서 신청 여부)
    @GetMapping("/api/myPages/selectList")
    public ResponseEntity<?> myPageList() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원가입(username) 중복 확인
    @GetMapping("/api/username-same-checks")
    public ResponseEntity<?> usernameSameCheck() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 스크랩 여부 확인
    @GetMapping("/api/scraps/{id}")
    public ResponseEntity<?> scrapList(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}



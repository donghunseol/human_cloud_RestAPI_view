package com.example.project_v2.user;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.apply.ApplyRepository;
import com.example.project_v2.apply.ApplyResponse;
import com.example.project_v2.notice.NoticeRepository;
import com.example.project_v2.notice.NoticeResponse;
import com.example.project_v2.resume.ResumeRepository;
import com.example.project_v2.resume.ResumeResponse;
import com.example.project_v2.scrap.ScrapRepository;
import com.example.project_v2.scrap.ScrapResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserRepository userRepository;
    private final UserJPARepository userJPARepository;
    private final HttpSession session;

    // 메인 화면
    @GetMapping("/")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원 가입
    @PostMapping("/user/join")
    public ResponseEntity<?> join(UserRequest.JoinDTO requestDTO) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<?> login(UserRequest.LoginDTO requestDTO) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원 정보 수정
    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 로그아웃
    @GetMapping("/api/user/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 마이페이지 메인 (공고, 이력서 출력)
    @GetMapping("/api/myPage")
    public ResponseEntity<?> myPage() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 마이 페이지 - 지원한 공고 (공고 출력 / 이력서 신청 여부)
    @GetMapping("/api/myPage/selectList")
    public ResponseEntity<?> myPageList() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 회원가입(username) 중복 확인
    @GetMapping("/api/username-same-check")
    public ResponseEntity<?> usernameSameCheck(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.ok(new ApiUtil<>(true));
        } else {
            return ResponseEntity.ok(new ApiUtil<>(false));
        }
    }

    // 스크랩 여부 확인
    @GetMapping("/api/scrap/{id}")
    public ResponseEntity<?> scrapList(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}



package com.example.project_v2.notice;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeJPARepository noticeJPARepository;
    private final HttpSession session;

    // 공고 목록 보기
    @GetMapping("/notices")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 작성
    @PostMapping("/api/notices")
    public ResponseEntity<?> save() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 상세 보기
    @GetMapping("/notices/{id}")
    public ResponseEntity<?> detail() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 삭제
    @DeleteMapping("/api/notices/{id}")
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 수정
    @PutMapping("/api/notices/{id}")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

}
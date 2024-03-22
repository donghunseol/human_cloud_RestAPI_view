package com.example.project_v2.resume;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final ResumeJPARepository resumeJPARepository;
    private final HttpSession session;

    // 이력서 회원 리스트 출력
    @GetMapping("/api/resumes/{id}")
    public ResponseEntity<?> index(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 작성
    @PostMapping("/api/resumes")
    public ResponseEntity<?> save( ) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 삭제
    @DeleteMapping("/api/resumes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 수정
    @PutMapping("/api/resumes/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

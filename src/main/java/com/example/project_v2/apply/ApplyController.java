package com.example.project_v2.apply;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ApplyController {

    private ApplyController applyController;
    private final HttpSession session;

    // 지원 합격 불합격 (지원하기 결과 값 입력)
    @PostMapping("/api/applies/pass/{id}")
    public ResponseEntity<?> resumePass(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 지원할 이력서 선택
    @GetMapping("/api/applies/{id}/resume-save")
    public ResponseEntity<?> resumeSave(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 지원 취소
    @DeleteMapping("/api/applies/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 지원하기
    @PostMapping("/api/applies/{id}")
    public ResponseEntity<?> save(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

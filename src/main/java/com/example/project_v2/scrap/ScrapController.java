package com.example.project_v2.scrap;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapJPARepository scrapJPARepository;
    private final HttpSession session;

    // 스크랩 삭제
    @DeleteMapping("/api/scraps/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 스크랩 등록
    @PostMapping("/api/scraps")
    public ResponseEntity<?> save() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
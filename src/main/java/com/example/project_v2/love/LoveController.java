package com.example.project_v2.love;

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
public class LoveController {

    private final LoveJPARepository loveJPARepository;
    private final HttpSession session;

    // 좋아요 입력
    @PostMapping("/api/loves")
    public ResponseEntity<?> save(){
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 좋아요 삭제
    @DeleteMapping("/api/loves/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

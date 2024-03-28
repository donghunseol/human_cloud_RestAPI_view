package com.example.project_v2.notice;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.skill.Skill;
import com.example.project_v2.skill.SkillRequest;
import com.example.project_v2.skill.SkillService;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final SkillService skillService;
    private final NoticeService noticeService;
    private final HttpSession session;

    // 공고 목록 보기
    @GetMapping("/notices")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 작성
    @PostMapping("/api/notices")
    public ResponseEntity<?> save(@RequestBody NoticeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Notice notice = noticeService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(notice));
    }

    // 공고 상세 보기
    @GetMapping("/notices/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        NoticeResponse.DetailDTO respDTO = noticeService.noticeDetail(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 공고 삭제
    @DeleteMapping("/api/notices/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        noticeService.delete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 수정
    @PutMapping("/api/notices/{id}")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

}
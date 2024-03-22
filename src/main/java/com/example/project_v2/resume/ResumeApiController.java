package com.example.project_v2.resume;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.scrap.ScrapRepository;
import com.example.project_v2.user.User;
import com.example.project_v2.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeApiController {

    private final ResumeJPARepository resumeJPARepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    // 이력서 회원 리스트 출력
    @GetMapping("/api/resume/{id}")
    public ResponseEntity<?> index(@PathVariable Integer id) {
        User user = userRepository.findById(id);

        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    // 이력서 작성
    @PostMapping("/api/resume")
    public ResponseEntity<?> save(ResumeRequest.ResumeDTO resume, @RequestParam(name = "skillNames") List<String> skillNames) {
        User userInfo = (User) session.getAttribute("sessionUser");
        resumeRepository.resumeSave(userInfo.getId(), resume, skillNames);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 삭제
    @DeleteMapping("/api/resume/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id) {
        resumeRepository.deleteByResumeId(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 수정
    @PutMapping("/api/resume/{id}")
    public ResponseEntity<?> update(ResumeRequest.ResumeDTO resume, @RequestParam(name = "skillNames") List<String> skillNames, @PathVariable Integer id) {
        resumeRepository.resumeUpdate(id, resume, skillNames);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

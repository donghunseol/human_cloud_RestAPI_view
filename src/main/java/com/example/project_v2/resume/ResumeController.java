package com.example.project_v2.resume;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final ResumeService resumeService;
    private final HttpSession session;

    // 이력서 회원 리스트 출력
    @GetMapping("/api/resumes/{id}")
    public ResponseEntity<?> index(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 작성
    @PostMapping("/api/resumes")
    public ResponseEntity<?> save(@RequestBody ResumeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Resume resume = resumeService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(resume));
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

    // 이력서 상세 보기
    @GetMapping("/api/resumes/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO reqDTO = resumeService.resumeDetail(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    // 이력서 리스트
    @GetMapping("/api/resumes")
    public ResponseEntity<?> resumeList() {
        List<ResumeResponse.ResumeListDTO> reqDTO = resumeService.resumeList();
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    // 이력서 리스트(개인)
    @GetMapping("/api/my-resumes")
    public ResponseEntity<?> myResumeList() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.ResumeListDTO> reqDTO = resumeService.resumeListByUser(sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }
}

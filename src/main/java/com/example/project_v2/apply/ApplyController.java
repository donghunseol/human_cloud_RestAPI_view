package com.example.project_v2.apply;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ApplyController {

    private final ApplyService applyService;
    private final HttpSession session;

    // 합격 불합격
    @PostMapping("/applies/pass/{id}")
    public ResponseEntity<?> resumePass(@PathVariable Integer id, @RequestBody ApplyRequest.PassDTO passDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiUtil<>(401, "로그인이 필요합니다."));
        } // 로그인 안되어 있으면 로그인 해야함

        if (sessionUser.getRole() != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiUtil<>(403, "권한이 없습니다."));
        } // 권한(기업 로그인 했을때만 유효)이 없으면 안됨

        ApplyResponse.DTO respDTO = applyService.resumePass(passDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 지원할 이력서 선택
    @GetMapping("/applies/{resumeId}/resume-save")
    public String resumeSave(@PathVariable Integer resumeId) {
        session.setAttribute("selectResume", resumeId);

        return "redirect:/";
    }

    // 지원 취소
    @PostMapping("/applies/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.delete(id, sessionUser.getId());
        return "myPage/select-list";
    }

    // 지원하기
    @PostMapping("/applies/{id}")
    public String save(ApplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.DTO respDTO = applyService.save(reqDTO, sessionUser);
        return "myPage/select-list";
    }
}


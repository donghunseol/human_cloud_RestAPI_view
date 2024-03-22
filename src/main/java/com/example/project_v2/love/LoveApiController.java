package com.example.project_v2.love;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LoveApiController {

    private final LoveJPARepository loveJPARepository;
    private final LoveRepository loveRepository;
    private final HttpSession session;

    // 좋아요 입력
    @PostMapping("/api/loves")
    public ResponseEntity<?> save(@RequestBody LoveRequest.SaveDTO requestDTO, HttpServletRequest response){
        // 인증
        User sessionUser = (User) session.getAttribute("sessionUser");

        Love love = loveRepository.save(requestDTO, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(love));
    }

    // 좋아요 삭제
    @DeleteMapping("/api/loves/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpServletRequest response){
//         인증
        User sessionUser = (User) session.getAttribute("sessionUser");


//         2. 권한 체크
        Love love = loveRepository.findById(id);

        loveRepository.deleteById(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}

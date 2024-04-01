package com.example.project_v2.scrap;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapJPARepository scrapJPARepository;
    private final HttpSession session;
    private final ScrapService scrapService;

    // 스크랩 삭제
    @DeleteMapping("/api/scraps/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 스크랩 등록
    @PostMapping("/api/scraps/{id}")
    public ResponseEntity<?> save(@PathVariable Integer id, @RequestBody ScrapRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Scrap scrap = scrapService.save(id, reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(scrap));
    }
}
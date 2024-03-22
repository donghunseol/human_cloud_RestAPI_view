package com.example.project_v2.scrap;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ScrapApiController {

    private final ScrapJPARepository scrapJPARepository;
    private final ScrapRepository scrapRepository;
    private final HttpSession session;

    // 스크랩 삭제
    @DeleteMapping("/api/scrap/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapRepository.deleteById(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 스크랩 등록
    @PostMapping("/api/scrap")
    public ResponseEntity<?> save(@RequestBody ScrapRequest.UserDTO userDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapResponse.DetailDTO scrapDTO = scrapRepository.findScrap(sessionUser.getId(), sessionUser.getId());
        session.setAttribute("scrap", scrapDTO);
        Scrap scrap = scrapRepository.save(sessionUser.getId(), userDTO);

        return ResponseEntity.ok(new ApiUtil<>(scrap));
    }
}
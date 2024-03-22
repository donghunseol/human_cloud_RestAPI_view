package com.example.project_v2.notice;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.resume.ResumeRepository;
import com.example.project_v2.scrap.ScrapRepository;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeApiController {

    private final NoticeJPARepository noticeJPARepository;
    private final HttpSession session;
    private final NoticeRepository noticeRepository;

    // 공고 목록 보기
    @GetMapping("/notice")
    public ResponseEntity<?> index(HttpServletRequest request, @RequestParam(defaultValue = "") String keyword) {
        List<NoticeResponse.DTO> noticeList = new ArrayList<>();
        User user = (User) session.getAttribute("sessionUser");
        noticeList = noticeRepository.findAll();
        request.setAttribute("noticeList", noticeList);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 작성
    @PostMapping("/api/notice/save")
    public ResponseEntity<?> save(HttpServletRequest request, NoticeRequest.NoticeDTO notice, @RequestParam(name = "skillNames") List<String> skillNames) {
        User userInfo = (User) session.getAttribute("sessionUser");
        noticeRepository.save(userInfo.getId(), notice, skillNames);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 상세 보기
    @GetMapping("/notice/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        NoticeResponse.DetailDTO responseDTO = noticeRepository.findNoticeById(id);
        System.out.println(responseDTO);
        session.setAttribute("notice", responseDTO);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 삭제
    @DeleteMapping("/api/notice/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        noticeRepository.deleteById(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 공고 수정
    @PutMapping("/api/notice/{id}")
    public ResponseEntity<?> update(NoticeRequest.NoticeDTO notice, @PathVariable(name = "id") Integer id,  @RequestParam(name = "skillNames") List<String> skillNames) {
        noticeRepository.update(id, notice, skillNames);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

}
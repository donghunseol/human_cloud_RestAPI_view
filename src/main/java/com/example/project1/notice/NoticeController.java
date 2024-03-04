package com.example.project1.notice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notice" )
    public String index(HttpServletRequest request) {
        List<Notice> noticeList = noticeRepository.findAll();
        request.setAttribute("noticeList", noticeList);

        return "index";
    }

    @GetMapping("/notice/saveForm")
    public String saveForm() {
        return "notice/saveForm";
    }

    @GetMapping("/notice/1")
    public String detail() {
        return "notice/detail";
    }
}
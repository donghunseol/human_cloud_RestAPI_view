package com.example.project1.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class NoticeController {
    @GetMapping("/notice" )
    public String index() {
        return "notice/main";
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

package com.example.project1.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    @GetMapping("resume" )
    public String index() {
        return "resume/main";
    }

    @GetMapping("/resume/saveForm")
    public String saveForm() {
        return "resume/saveForm";
    }

    @GetMapping("board/updateForm")
    public String updateForm(){
        return "resume/updateForm";
    }
}

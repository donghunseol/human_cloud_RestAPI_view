package com.example.project1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    @GetMapping("board" )
    public String index() {
        return "board/main";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("board/updateForm")
    public String updateForm(){
        return "board/updateForm";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }
}

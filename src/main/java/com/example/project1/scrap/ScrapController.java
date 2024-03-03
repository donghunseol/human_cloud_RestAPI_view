package com.example.project1.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ScrapController {
    @GetMapping("/scrap" )
    public String index() {
        System.out.println("테스트용 sout");

        return "scrap/main";
    }
}

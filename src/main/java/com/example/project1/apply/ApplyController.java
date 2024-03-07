package com.example.project1.apply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyRepository applyRepository;
    @PostMapping("/apply/{id}/delete")
    public String delete(@PathVariable Integer id) {
        System.out.println(111);
        applyRepository.deleteById(id);
        System.out.println(22222);

        return "redirect:/myPage/selectList";
    }
}

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
        applyRepository.deleteById(id);

        return "redirect:/myPage/selectList";
    }

    @PostMapping("/apply/{id}/save")
    public String save(@PathVariable Integer id, ApplyRequest.SaveDTO requestDTO){
        System.out.println(1111);
        System.out.println(requestDTO);

        applyRepository.save(requestDTO);
        System.out.println(222222);

        return "redirect:/myPage/selectList";
    }
}

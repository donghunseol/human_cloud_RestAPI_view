package com.example.project1.apply;

import com.example.project1.notice.NoticeRepository;
import com.example.project1.resume.ResumeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyRepository applyRepository;
    private final ResumeRepository resumeRepository;
    private final HttpSession session;

    @GetMapping("/apply/{id}/resumeSave")
    public String resumeSave(@PathVariable Integer id, HttpServletRequest request){
        session.setAttribute("selectResume", resumeRepository.findByResumeId(id));
        System.out.println(resumeRepository.findByResumeId(id));
        return "redirect:/myPage";
    }

    @PostMapping("/apply/{id}/delete")
    public String delete(@PathVariable Integer id) {
        applyRepository.deleteById(id);

        return "redirect:/myPage/selectList";
    }

    @PostMapping("/apply/{id}/save")
    public String save(@PathVariable Integer id, ApplyRequest.SaveDTO requestDTO){
        applyRepository.save(requestDTO);

        return "redirect:/myPage/selectList";
    }
}

package com.example.project1.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeRepository resumeRepository;

    @GetMapping("/resume" )
    public String index() {
        return "resume/main";
    }

    @GetMapping("/resume/saveForm")
    public String saveForm(HttpServletRequest request) {
        
        request.setAttribute("skillList", "디비에서조회된 모든 스킬들 컬렉션");
        return "resume/saveForm";
    }

    @GetMapping("/resume/updateForm")
    public String updateForm(){
        return "resume/updateForm";
    }






    // REST API
    @GetMapping("/api/resume")
    public List<ResumeResponse.DTO> findAll() {
        return resumeRepository.findAll();
    }

    @GetMapping("/api/user/resume/{id}")
    public List<ResumeResponse.DTO> findAllByUserId(@PathVariable Integer id) {
        return resumeRepository.findAllByUserId(id);
    }

    @GetMapping("/api/resume/{id}")
    public ResumeResponse.DetailDTO findByResumeId(@PathVariable Integer id) {
        return resumeRepository.findByResumeId(id);
    }
}

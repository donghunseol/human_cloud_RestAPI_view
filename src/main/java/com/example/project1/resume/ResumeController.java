package com.example.project1.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Controller
public class ResumeController {
    private final ResumeRepository resumeRepository;

    @GetMapping("/resume" )
    public String index() {
        return "resume/main";
    }

    @GetMapping("/resume/saveForm")
    public String saveForm() {
        return "resume/saveForm";
    }

    @GetMapping("/resume/updateForm")
    public String updateForm(){
        return "resume/updateForm";
    }






    // REST API
    @GetMapping("/api/resume")
    public List<ResumeResponse.ResumeListDTO> findAll() {
        return resumeRepository.findAll();
    }

    @GetMapping("/api/user/resume/{id}")
    public List<ResumeResponse.ResumeListDTO> findAllByUserId(@PathVariable Integer id) {
        return resumeRepository.findAllByUserId(id);
    }

    @GetMapping("/api/resume/{id}")
    public ResumeResponse.ResumeDetailDTO findByResumeId(@PathVariable Integer id) {
        return resumeRepository.findByResumeId(id);
    }
}

package com.example.project1.resume;

import com.example.project1.scrap.ScrapRepository;
import com.example.project1.scrap.ScrapRequest;
import com.example.project1.user.User;
import com.example.project1.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    @GetMapping("/resume/{id}")
    public String index(@PathVariable Integer id) {
        User user = userRepository.findById(id);

        return "resume/main";
    }

    @GetMapping("/resume/{id}/detailForm")
    public String detailForm(HttpServletRequest request, @PathVariable Integer id) {
        ResumeResponse.DetailDTO resumeDetail = resumeRepository.findByResumeId(id);
        request.setAttribute("resumeDetail", resumeDetail);
        return "/resume/detailForm";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(HttpServletRequest request, @PathVariable Integer id) {
        resumeRepository.deleteByResumeId(id);
        return "redirect:/myPage";
    }

    @GetMapping("/resume/saveForm")
    public String saveForm(HttpServletRequest request) {
        return "resume/saveForm";
    }

    @PostMapping("/resume/save")
    public String save(HttpServletRequest request, ResumeRequest.resumeDTO
            resume) {
        System.out.println(11111);
        resumeRepository.resumeSave(1, resume);
        System.out.println(222222);
        return "redirect:/myPage";
    }

    @GetMapping("/resume/updateForm")
    public String updateForm() {
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

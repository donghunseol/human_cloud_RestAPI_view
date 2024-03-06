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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    @GetMapping("/resume/{id}")
    public String index(@PathVariable Integer id, ScrapRequest.CompanyDTO companyDTO, ScrapRequest.IndividualDTO individualDTO) {
        User user = userRepository.findById(id);
        if (user.getRole() == 0) {

        }
        scrapRepository.companySave(companyDTO);
        scrapRepository.individualSave(individualDTO);
        return "resume/main";
    }

    @GetMapping("/resume/{id}/detailForm")
    public String detailForm(HttpServletRequest request, @PathVariable Integer id) {
        ResumeResponse.DetailDTO resumeDetail = resumeRepository.findByResumeId(id);
        request.setAttribute("resumeDetail", resumeDetail);
        return "/resume/detailForm";
    }

    @GetMapping("/resume/saveForm")
    public String saveForm(HttpServletRequest request) {
        return "resume/saveForm";
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

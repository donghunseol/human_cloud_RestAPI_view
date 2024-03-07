package com.example.project1.resume;

import com.example.project1.scrap.ScrapRepository;
<<<<<<< HEAD
import com.example.project1.scrap.ScrapRequest;
import com.example.project1.skill.SkillRepository;
import com.example.project1.skill.SkillRequest;
=======
>>>>>>> 90d2172f5a17319dc7cc2f6d9e9186904a932643
import com.example.project1.user.User;
import com.example.project1.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
<<<<<<< HEAD
    private final SkillRepository skillRepository;
=======
    private final HttpSession session;
>>>>>>> 90d2172f5a17319dc7cc2f6d9e9186904a932643

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
        User userInfo = (User) session.getAttribute("sessionUser");
        request.setAttribute("userInfo", userInfo);
        System.out.println(userInfo);
        return "resume/saveForm";
    }

//    @PostMapping("/resume/save")
//    public String save(HttpServletRequest request, ResumeRequest.ResumeDTO resume) {
//        User userInfo = (User) session.getAttribute("sessionUser");
//        System.out.println(11111);
//        resumeRepository.resumeSave(userInfo.getId(), resume);
//        System.out.println(222222);
//        return "redirect:/myPage";
//    }
//
//    @PostMapping("/resume/skillSave")
//    public String skillSave(HttpServletRequest request, @RequestParam(name = "skillNames") List<String> skillNames) {
//        resumeRepository.skillSave(skillNames);
//        return "redirect:/myPage";
//    }

    @PostMapping("/resume/save")
    public String save(HttpServletRequest request, ResumeRequest.ResumeDTO resume, @RequestParam(name = "skillNames") List<String> skillNames) {
        User userInfo = (User) session.getAttribute("sessionUser");
        resumeRepository.resumeSave(userInfo.getId(), resume, skillNames);

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

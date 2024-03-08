package com.example.project1.resume;

import com.example.project1.scrap.ScrapRepository;
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
    private final HttpSession session;

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
        return "resume/saveForm";
    }

    @PostMapping("/resume/save")
    public String save(ResumeRequest.ResumeDTO resume, @RequestParam(name = "skillNames") List<String> skillNames) {
        User userInfo = (User) session.getAttribute("sessionUser");
        resumeRepository.resumeSave(userInfo.getId(), resume, skillNames);

        return "redirect:/myPage";
    }

    @GetMapping("/resume/{id}/updateForm")
    public String updateForm(HttpServletRequest request, @PathVariable Integer id) {
        ResumeResponse.DetailDTO updateDetail = resumeRepository.findByResumeId(id);
        request.setAttribute("updateDetail", updateDetail);
        System.out.println(updateDetail);
        return "resume/updateForm";
    }

    @PostMapping("/resume/{id}/updateForm")
    public String update(ResumeRequest.ResumeDTO resume, @RequestParam(name = "skillNames") List<String> skillNames, @PathVariable Integer id) {
        resumeRepository.resumeUpdate(id, resume, skillNames);
        return "redirect:/myPage";
    }
}

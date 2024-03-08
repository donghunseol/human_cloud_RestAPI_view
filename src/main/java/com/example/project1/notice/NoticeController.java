package com.example.project1.notice;

import com.example.project1.resume.ResumeRepository;
import com.example.project1.resume.ResumeResponse;
import com.example.project1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final HttpSession session;
    private final NoticeRepository noticeRepository;
    private final ResumeRepository resumeRepository;

    @GetMapping("/notice")
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "") String keyword) {
        List<ResumeResponse.DTO> resumeList = new ArrayList<>();
        List<NoticeResponse.DTO> noticeList = new ArrayList<>();
        User user = (User) session.getAttribute("sessionUser");

        if (user == null) {
            return "user/loginForm";
        }

        if (user.getRole() == 0) {
            if (keyword.isBlank()) {
                resumeList = resumeRepository.findAll();
            } else {
                resumeList = resumeRepository.findSearchAll(keyword);
            }
            request.setAttribute("resumeList", resumeList);
            return "index";
        }

        if (keyword.isBlank()) {
            noticeList = noticeRepository.findAll();
        } else {
            noticeList = noticeRepository.findSearchAll(keyword);
        }
        request.setAttribute("noticeList", noticeList);
        
        return "index";
    }

    @GetMapping("/notice/saveForm")
    public String saveForm(HttpServletRequest request) {
        User userInfo = (User) session.getAttribute("sessionUser");
        request.setAttribute("userInfo", userInfo);
        System.out.println(userInfo);

        // 위 주소로 다이렉트하게 접속하면 session에 값이 저장되지 않아서 에러가 발생할 수 있다.
        // -> /notice를 거쳐가면 오류가 안 난다.
        return "notice/saveForm";
    }

    @PostMapping("/notice/save")
    public String save(HttpServletRequest request, NoticeRequest.NoticeDTO notice, @RequestParam(name = "skillNames") List<String> skillNames) {
        User userInfo = (User) session.getAttribute("sessionUser");
        noticeRepository.save(userInfo.getId(), notice, skillNames);

        return "redirect:/notice";
    }

    @GetMapping("/notice/{id}")
    public String detail(@PathVariable Integer id) {
        NoticeResponse.DetailDTO responseDTO = noticeRepository.findNoticeById(id);
        session.setAttribute("notice", responseDTO);

        return "notice/detail";
    }

    @PostMapping("/notice/{id}/delete")
    public String delete(@PathVariable Integer id) {
        noticeRepository.deleteById(id);

        return "redirect:/notice";
    }

    @GetMapping("/notice/{id}/updateForm")
    public String updateForm(@PathVariable(name = "id") Integer id) {
        NoticeResponse.DetailDTO responseDTO = noticeRepository.findNoticeById(id);
        session.setAttribute("notice", responseDTO);

        return "/notice/updateForm";
    }

    @PostMapping("/notice/{id}/update")
    public String update(NoticeRequest.NoticeDTO notice, @PathVariable(name = "id") Integer id) {
        noticeRepository.update(notice, id);

        return "redirect:/notice/" + id;
    }

}
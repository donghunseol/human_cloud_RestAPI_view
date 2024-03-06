package com.example.project1.notice;

import com.example.project1.user.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final HttpSession session;
    private final NoticeRepository noticeRepository;

    @GetMapping("/notice" )
    public String index(HttpServletRequest request) {
        List<Notice> noticeList = noticeRepository.findAll();
        request.setAttribute("noticeList", noticeList);

        return "index";
    }

    @GetMapping("/notice/saveForm")
    public String saveForm() {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        if(sessionUser == null){
//            return "redirect:user/loginForm";
//        }

        return "notice/saveForm";
    }

    @PostMapping("/notice/save")
    public String save(NoticeRequest.SaveDTO requestDTO, HttpServletRequest request){
        // 인증체크
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if(sessionUser == null){
//            return "redirect:user/loginForm";
//        }

        // Model 위임
        noticeRepository.save(requestDTO, 1);

        return "redirect:/notice";
    }

    @GetMapping("/notice/{id}")
    public String detail(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        NoticeResponse.DetailDTO responseDTO = noticeRepository.findNoticeById(id);
        request.setAttribute("notice", responseDTO);
        System.out.println(responseDTO);

        return "notice/detail";
    }

    @PostMapping("/notice/{id}/delete")
    public String delete(@PathVariable Integer id) {
        noticeRepository.deleteById(id);

        return "redirect:/notice";
    }

    @GetMapping("/notice/{id}/updateForm")
    public String updateForm(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        NoticeResponse.DetailDTO responseDTO = noticeRepository.findNoticeById(id);
        request.setAttribute("notice", responseDTO);

        return "/notice/updateForm";
    }

    @PostMapping("/notice/{id}/update")
    public String update(NoticeRequest.UpdateDTO updateDTO_n, UserRequest.UpdateNoticeDTO updateDTO_u, @PathVariable(name = "id") Integer id) {
        System.out.println(updateDTO_n);
        System.out.println(updateDTO_u);
        noticeRepository.update(updateDTO_n, updateDTO_u, id);

        return "redirect:/notice/"+id;
    }

    @GetMapping("/notice/main")
    public String main(@PathVariable(name = "id") Integer id){
        return "notice/main";
    }
}
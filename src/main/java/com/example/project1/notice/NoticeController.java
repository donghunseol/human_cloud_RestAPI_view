package com.example.project1.notice;

import com.example.project1.scrap.ScrapRepository;
import com.example.project1.scrap.ScrapResponse;
import com.example.project1.user.User;
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
    private final ScrapRepository scrapRepository;

    @GetMapping("/notice")
    public String index() {
        List<Notice> noticeList = noticeRepository.findAll();
        session.setAttribute("noticeList", noticeList);

        return "index";
    }

    @GetMapping("/notice/saveForm")
    public String saveForm() {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if(sessionUser == null){
//            return "redirect:user/loginForm";
//        }

        // 위 주소로 다이렉트하게 접속하면 session에 값이 저장되지 않아서 에러가 발생할 수 있다.
        // -> /notice를 거쳐가면 오류가 안 난다.
        return "notice/saveForm";
    }

    @PostMapping("/notice/save")
    public String save(NoticeRequest.SaveDTO requestDTO, HttpServletRequest request) {
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
    public String detail(@PathVariable(name = "id") Integer id) {
        NoticeResponse.DetailDTO responseDTO = noticeRepository.findNoticeById(id);
        System.out.println(responseDTO);
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
    public String update(NoticeRequest.UpdateDTO updateDTO, @PathVariable(name = "id") Integer id) {
        noticeRepository.update(updateDTO, id);

        return "redirect:/notice/" + id;
    }

}
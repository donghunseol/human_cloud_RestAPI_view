package com.example.project_v2.notice;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;
    private final HttpSession session;

//
//    // 로그인한 유저가 작성한 공고 목록 보기
//    @GetMapping("/notices/my-notices")
//    public String myNoticeList(@RequestParam(defaultValue = "0") int page,
//                                          @RequestParam(defaultValue = "10") int size,
//                                          @RequestParam(defaultValue = "id") String sortBy,
//                                          @RequestParam(defaultValue = "desc") String direction,
//                                          HttpServletRequest request) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        List<NoticeResponse.NoticeListDTO> respDTO = noticeService.noticeListByUser(sessionUser, pageable);
//        request.setAttribute("noticeList", respDTO);
//        return "/";
//    }

//    // 공고 목록 보기
//    @GetMapping("/notices")
//    public String index(@RequestParam(defaultValue = "0") int page,
//                                   @RequestParam(defaultValue = "10") int size,
//                                   @RequestParam(defaultValue = "id") String sortBy,
//                                   @RequestParam(defaultValue = "desc") String direction,
//                                   HttpServletRequest request) {
//        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        List<NoticeResponse.NoticeListDTO> respDTO = noticeService.noticeList(pageable);
//        request.setAttribute("noticeList", respDTO);
//        return "index";
//    }

    @GetMapping("/api/notice/save-form")
    public String saveForm(HttpServletRequest request) {
        User userInfo = (User) session.getAttribute("sessionUser");
        request.setAttribute("userInfo", userInfo);
        // 위 주소로 다이렉트하게 접속하면 session에 값이 저장되지 않아서 에러가 발생할 수 있다. "/notice"를 거쳐가자
        return "notice/save-form";
    }

    // 공고 작성
    @PostMapping("/api/notices")
    public String save(NoticeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        noticeService.save(reqDTO, sessionUser);
        return "redirect:/";
    }

    // 공고 상세 보기
    @GetMapping("/notices/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        NoticeResponse.DetailDTO respDTO = noticeService.noticeDetail(id, sessionUser);
        request.setAttribute("notice", respDTO);
        return "notice/detail";
    }

    // 공고 삭제
    @PostMapping("/notice/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        noticeService.delete(id, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/notice/{id}/update-form")
    public String updateForm(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        NoticeResponse.DetailDTO responseDTO = noticeService.noticeDetail(id, sessionUser);
        request.setAttribute("notice", responseDTO);
        return "/notice/update-form";
    }

    // 공고 수정
    @PostMapping("/api/notices/{id}")
    public String update(@PathVariable Integer id, NoticeRequest.UpdateDTO reqDTO, @RequestParam("skillNames") List<String> skillNames) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        reqDTO.setSkills(skillNames.stream().map(skillName -> {
            NoticeRequest.UpdateDTO.SkillDTO skillDTO = new NoticeRequest.UpdateDTO.SkillDTO();
            skillDTO.setName(skillName);
            return skillDTO;
        }).collect(Collectors.toList()));
        noticeService.update(id, reqDTO, sessionUser);
        return "redirect:/notices/" + id;
    }
}
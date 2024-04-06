package com.example.project_v2.resume;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final ResumeService resumeService;
    private final HttpSession session;

//    // 이력서 회원 리스트 출력
//    @GetMapping("/resumes/{id}")
//    public ResponseEntity<?> index(@PathVariable Integer id) {
//        return ResponseEntity.ok(new ApiUtil<>(null));
//    }

//    // 이력서 리스트
//    @GetMapping("/resumes")
//    public ResponseEntity<?> resumeList(@RequestParam(defaultValue = "0") int page,
//                                        @RequestParam(defaultValue = "10") int size,
//                                        @RequestParam(defaultValue = "id") String sortBy,
//                                        @RequestParam(defaultValue = "desc") String direction) {
//        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        List<ResumeResponse.ResumeListDTO> respDTO = resumeService.resumeList(pageable);
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

//    // 이력서 리스트(개인)
//    @GetMapping("/resumes/my-resumes")
//    public ResponseEntity<?> myResumeList(@RequestParam(defaultValue = "0") int page,
//                                          @RequestParam(defaultValue = "10") int size,
//                                          @RequestParam(defaultValue = "id") String sortBy,
//                                          @RequestParam(defaultValue = "desc") String direction) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        List<ResumeResponse.ResumeListDTO> respDTO = resumeService.resumeListByUser(sessionUser, pageable);
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

    @GetMapping("/resumes/save-form")
    public String saveForm(HttpServletRequest request) {
        User userInfo = (User) session.getAttribute("sessionUser");
        request.setAttribute("userInfo", userInfo);
        // 위 주소로 다이렉트하게 접속하면 session에 값이 저장되지 않아서 에러가 발생할 수 있다. "/resume"를 거쳐가자
        return "resume/save-form";
    }

    // 이력서 작성
    @PostMapping("/resumes/save")
    public String save(ResumeRequest.SaveDTO reqDTO, @RequestParam("skillNames") List<String> skillNames) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.save(reqDTO, sessionUser, skillNames);
        return "redirect:/";
    }

    // 이력서 삭제
    @PostMapping("/resumes/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(id, sessionUser.getId());
        return "myPage/main";
    }

    // 이력서 수정 페이지
    @GetMapping("/resumes/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.resumeDetail(id, sessionUser);
        request.setAttribute("updateDetail", respDTO);
        return "resume/update-form";
    }

    // 이력서 수정
    @PostMapping("/resumes/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO, @RequestParam("skillNames") List<String> skillNames) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        reqDTO.toEntity(sessionUser);
        resumeService.update(id, sessionUser, reqDTO, skillNames);
        return "myPage/main";
    }

    // 이력서 상세 보기
    @GetMapping("/resumes/{id}/detail")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.resumeDetail(id, sessionUser);
        request.setAttribute("resumeDetail", respDTO);
        return "resume/detail";
    }
}

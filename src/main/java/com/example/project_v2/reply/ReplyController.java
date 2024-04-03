package com.example.project_v2.reply;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    // 댓글 작성
    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/users/login-form";
        }
        replyService.save(reqDTO, sessionUser);
        return "redirect:/boards/" + reqDTO.getBoardId();
    }

    // 댓글 삭제
    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/login-form";
        }
        replyService.delete(id, sessionUser.getId());

        // 삭제된 해당 게시판으로 이동
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        } else {
            return "redirect:/board"; // 이전 페이지가 없을 경우 기본 페이지로 이동
        }
    }
}
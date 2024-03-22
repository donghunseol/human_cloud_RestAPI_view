package com.example.project_v2.reply;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReplyApiController {

    private final ReplyJPARepository replyJPARepository;
    private final HttpSession session;
    private final ReplyRepository replyRepository;


    // 댓글 작성
    @PostMapping("/api/reply/save")
    public ResponseEntity<?> save(ReplyRequest.WriteDTO requestDTO) {
        System.out.println(requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            return ResponseEntity.ok(new ApiUtil<>(null));
        }

        replyRepository.save(requestDTO, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 댓글 삭제
    @DeleteMapping("/api/reply/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return ResponseEntity.ok(new ApiUtil<>(null));
        }
        Reply reply = replyRepository.findById(id);

        replyRepository.deleteById(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
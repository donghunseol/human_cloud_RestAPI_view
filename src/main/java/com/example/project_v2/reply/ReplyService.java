package com.example.project_v2.reply;

import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;

    @Transactional
    public void 댓글삭제 (int replyId,int sessionUserId){
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(()-> new Exception404("없는 댓글을 삭제할 수 없어요."));

        if (reply.getUser().getId() != sessionUserId){
            throw new Exception403("댓글을 삭제할 권한이 없어요");

        }

        replyJPARepository.deleteById(replyId);

    }



}

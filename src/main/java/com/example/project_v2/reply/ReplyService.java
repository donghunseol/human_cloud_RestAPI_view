package com.example.project_v2.reply;

import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.board.Board;
import com.example.project_v2.board.BoardJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final BoardJPARepository boardJPARepository;
    private final ReplyJPARepository replyJPARepository;

    @Transactional
    public Reply 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("없는 게시글에 댓글을 작성할 수 없어요"));

        Reply reply = reqDTO.toEntity(sessionUser, board);

        replyJPARepository.save(reply);
        return reply;
    }
}

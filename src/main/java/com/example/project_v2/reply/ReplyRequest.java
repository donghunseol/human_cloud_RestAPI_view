package com.example.project_v2.reply;

import com.example.project_v2.board.Board;
import com.example.project_v2.user.User;
import lombok.Data;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}

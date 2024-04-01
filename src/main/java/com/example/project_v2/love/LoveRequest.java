package com.example.project_v2.love;

import com.example.project_v2.board.Board;
import com.example.project_v2.user.User;
import lombok.Data;

public class LoveRequest {

    @Data
    public static class SaveDTO{
        private User user;
        private Board board;

        public Love toEntity(User sessionUser, Board board){
            return Love.builder()
                    .user(sessionUser)
                    .board(board)
                    .build();
        }
    }
}

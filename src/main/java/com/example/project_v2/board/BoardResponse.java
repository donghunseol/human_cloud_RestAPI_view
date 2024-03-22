package com.example.project_v2.board;

import com.example.project_v2.love.Love;
import com.example.project_v2.reply.Reply;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {
    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;

        private List<ReplyDTO> replies = new ArrayList<>();
        private List<LoveDTO> loves = new ArrayList<>();

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply)).toList();
            this.loves = board.getLoves().stream().map(love -> new LoveDTO(love)).toList();
        }

        @Data
        public static class ReplyDTO{
            private int id;
            private String comment;
            private int userId;
            private String username;

            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
            }
        }

        @Data
        public static class LoveDTO{
            private int id;
            private int userId;
            private int boardId;

            public LoveDTO(Love love) {
                this.id = love.getId();
                this.userId = love.getUser().getId();
                this.boardId = love.getBoard().getId();
            }
        }
    }
}

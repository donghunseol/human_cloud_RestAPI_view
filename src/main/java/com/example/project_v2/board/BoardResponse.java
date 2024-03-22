package com.example.project_v2.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class MainDTO{

        private int id; // 게시판 번호
        private String title; // 게시글 제목
        private String username; // 작성자


        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.username = board.getUser().getUsername();
        }
    }
}

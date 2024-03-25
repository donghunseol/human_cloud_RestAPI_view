package com.example.project_v2.board;

import com.example.project_v2.user.User;
import lombok.Data;

public class BoardRequest {
    @Data
    public static class SaveDTO{
        private String title;
        private String content;

        // 빌더 패턴
        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO{
        private Integer id; // board의 pk
        private String title;
        private String content;
    }
}

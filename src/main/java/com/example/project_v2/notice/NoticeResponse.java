package com.example.project_v2.notice;

import lombok.Data;

public class NoticeResponse {

    @Data
    public static class MainDTO{
        private Integer noticeId;
        private String username;
        private String title;
        private String deadline;
        private String image;

        public MainDTO(Notice notice) {
            this.noticeId = noticeId;
            this.username = username;
            this.title = title;
            this.deadline = deadline;
            this.image = image;
        }
    }
}
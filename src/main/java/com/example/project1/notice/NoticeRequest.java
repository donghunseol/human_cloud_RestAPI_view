package com.example.project1.notice;

import lombok.Data;

public class NoticeRequest {
    @Data
    public static class SaveDTO{
        private String title;
        private String type;
        private String field;
        private String work_place;
        private String content;
        private String deadline;
    }

    @Data
    public static class UpdateDTO{
        private String title;
        private String type;
        private String field;
        private String work_place;
        private String content;
        private String deadline;
    }
}

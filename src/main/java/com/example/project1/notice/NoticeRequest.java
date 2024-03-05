package com.example.project1.notice;

import lombok.Data;

public class NoticeRequest {
    @Data
    public static class SaveDTO{
        private String title;
        private String type;
        private String field;
        private String workPlace;
        private String content;
        private String deadline;
    }
}

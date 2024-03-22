package com.example.project_v2.notice;

import lombok.Data;

import java.util.List;

public class NoticeRequest {
    @Data
    public static class NoticeDTO {
        private String title;
        private String type;
        private String field;
        private String workPlace;
        private String content;
        private String deadline;
    }

    @Data
    public static class SkillNameDTO {
        private List<String> skillNames;
    }

}

package com.example.project1.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapResponse {

    @AllArgsConstructor
    @Data
    public static class ScrapDTO{
        private Integer scrapId;
        private Integer sUserId;
        private String username;
        private Integer nUserId;
        private String nName;
        private String content;
        private String deadline;
        private String field;
        private String title;
        private String type;
        private Integer role;
    }

    // 스크랩 버튼 활성화 DTO
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private Boolean isScrap;
    }
}
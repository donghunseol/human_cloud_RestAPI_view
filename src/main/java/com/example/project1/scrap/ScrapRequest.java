package com.example.project1.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapRequest {

    // 기업 스크랩 DTO
    @AllArgsConstructor
    @Data
    public static class CompanyDTO{
        private Integer user_id;
        private Integer resume_id;
        private Integer role;
    }

    // 개인 스크랩 DTO
    @AllArgsConstructor
    @Data
    public static class IndividualDTO{
        private Integer user_id;
        private Integer notice_id;
        private Integer role;
    }
}

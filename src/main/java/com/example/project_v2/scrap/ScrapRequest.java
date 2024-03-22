package com.example.project_v2.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapRequest {

    // 기업 스크랩 DTO
    @AllArgsConstructor
    @Data
    public static class CompanyDTO{
        private Integer userId;
        private Integer resumeId;
        private Integer role;
    }

    // 개인 스크랩 DTO
    @AllArgsConstructor
    @Data
    public static class UserDTO{
        private Integer userId;
        private Integer noticeId;
        private Integer role;
    }
}

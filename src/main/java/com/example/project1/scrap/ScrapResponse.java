package com.example.project1.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapResponse {
    // 스크랩DTO
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private Boolean isScrap;
    }
}
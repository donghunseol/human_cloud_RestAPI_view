package com.example.project1.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapResponse {
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private Boolean isScrap;
        private Long scrapCount;
    }
}
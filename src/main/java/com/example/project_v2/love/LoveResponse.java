package com.example.project_v2.love;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LoveResponse {

    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private Boolean isLove;
        private Long loveCount;
    }
}

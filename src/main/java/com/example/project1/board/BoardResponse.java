package com.example.project1.board;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

public class BoardResponse {

    @Data
    public static class DetailDTO{
        private Integer id;
        private Integer user_id;
        private String title;
        private String content;
        private String username;
    }
}

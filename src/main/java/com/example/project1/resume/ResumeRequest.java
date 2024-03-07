package com.example.project1.resume;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

public class ResumeRequest {
    @Data
    public static class SaveDTO{
        private Integer id;
        private Integer userId;
        private String title;
        private String career;
        private String license;
        private String education;
        private String major;
        private Timestamp createdAt;
    }
}

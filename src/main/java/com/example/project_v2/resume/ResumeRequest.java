package com.example.project_v2.resume;

import lombok.Data;

import java.util.List;

public class ResumeRequest {
    @Data
    public static class ResumeDTO {
        public String title;
        public String education;
        public String major;
        public String license;
        public String career;
    }

    @Data
    public static class SkillNameDTO {
        private List<String> skillNames;
    }
}

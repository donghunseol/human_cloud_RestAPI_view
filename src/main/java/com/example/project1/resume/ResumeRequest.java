package com.example.project1.resume;

import lombok.Data;

public class ResumeRequest {
    @Data
    public static class resumeDTO {
        public Integer resumeId;
        public String title;
        public String education;
        public String major;
        public String license;
        public String career;
    }
}

package com.example.project_v2.resume;

import com.example.project_v2.user.User;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {
    @Data
    public static class SaveDTO {
        private Integer id;
        private User user;
        private String title;
        private String career;
        private String license;
        private String education;
        private String major;
        private List<SkillDTO> skills = new ArrayList<>();

        @Data
        public static class SkillDTO{
            private Integer id;
            private String name;
            private Integer role;
            private Integer resumeId;
        }

        public Resume toEntity(User user) {
            Resume resume = new Resume();
            resume.setUser(user);
            resume.setTitle(this.title);
            resume.setCareer(this.career);
            resume.setLicense(this.license);
            resume.setEducation(this.education);
            resume.setMajor(this.major);

            return resume;
        }
    }

    @Data
    public static class UpdateDTO{
        private Integer id; // resume의 pk
        private User user;
        private String title;
        private String career;
        private String license;
        private String education;
        private String major;
        private List<SkillDTO> skills = new ArrayList<>();

        @Data
        public static class SkillDTO{
            private Integer id;
            private String name;
            private Integer role;
            private Integer resumeId;
        }
    }
}

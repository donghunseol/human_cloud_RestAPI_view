package com.example.project1.resume;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResumeResponse {
    @Data
    public static class ResumeListDTO {
        private Integer id;
        private Integer user_id;
        private String title;
        private String name;
        private String image;

        private List<SkillDTO> skills = new ArrayList<>();

        public ResumeListDTO(Integer id, Integer user_id, String title, String name, String image) {
            this.id = id;
            this.user_id = user_id;
            this.title = title;
            this.name = name;
            this.image = image;
        }

        public void addSkill(SkillDTO skillDTO){
            skills.add(skillDTO);
        }
    }

    @Data
    public static class ResumeDetailDTO {
        private Integer id;
        private Integer user_id;
        private String title;
        private String name;
        private String birth;
        private String tel;
        private String email;
        private String address;
        private String image;
        private String education;
        private String major;
        private String license;
        private String career;
        private Integer role;
        private LocalDateTime created_at;

        private List<SkillDTO> skills = new ArrayList<>();

        public ResumeDetailDTO(Integer id, Integer user_id, String title, String name, String birth, String tel, String email, String address, String image, String education, String major, String license, String career, Integer role) {
            this.id = id;
            this.user_id = user_id;
            this.title = title;
            this.name = name;
            this.birth = birth;
            this.tel = tel;
            this.email = email;
            this.address = address;
            this.image = image;
            this.education = education;
            this.major = major;
            this.license = license;
            this.career = career;
            this.role = role;
        }

        public void addSkill(SkillDTO skillDTO){
            skills.add(skillDTO);
        }
    }

    @AllArgsConstructor
    @Data
    public static class SkillDTO {
        private Integer id;
        private String name;
    }
}

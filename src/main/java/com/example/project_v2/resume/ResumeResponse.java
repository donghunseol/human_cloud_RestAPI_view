package com.example.project_v2.resume;

import com.example.project_v2.skill.Skill;
import com.example.project_v2.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeResponse {
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String email;
        private String tel;
        private String address;
        private String career;
        private String license;
        private String education;
        private String major;
        private int userId;
        private boolean isResumeOwner;

        private List<SkillDTO> skills = new ArrayList<>();


        public DetailDTO(Resume resume, User sessionUser) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.email = resume.getUser().getEmail();
            this.tel = resume.getUser().getTel();
            this.address = resume.getUser().getAddress();
            this.career = resume.getCareer();
            this.license = resume.getLicense();
            this.education = resume.getEducation();
            this.major = resume.getMajor();
            this.userId = resume.getUser().getId();
            this.isResumeOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == userId) {
                    isResumeOwner = true;
                }
            }
            this.skills = resume.getSkills().stream().map(skill -> new SkillDTO(skill)).toList();
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private int resumeId;
            private String name;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.resumeId = skill.getResume().getId();
                this.name = skill.getName();
            }
        }
    }
}

package com.example.project_v2.resume;

import com.example.project_v2.skill.Skill;
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
            private Resume resume;
            private String name;
            private Integer role;

            public Skill toEntity(){
                return Skill.builder()
                        .resume(resume)
                        .name(name)
                        .role(role)
                        .build();
            }
        }

        public Resume toEntity(User sessionUser){
            List<Skill> skillList = this.skills.stream()
                    .map(SkillDTO::toEntity) // SkillDTO를 Skill 엔티티로 변환
                    .toList();

            return Resume.builder()
                    .user(sessionUser)
                    .title(title)
                    .career(career)
                    .license(license)
                    .education(education)
                    .major(major)
                    .skills(skillList)
                    .build();
        }
    }
}

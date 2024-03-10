package com.example.project1.notice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NoticeResponse {

    @Data
    public static class DTO {
        private Integer noticeId;
        private String username;
        private String title;
        private String deadline;

        private List<SkillDTO> skills = new ArrayList<>();

        public DTO(Integer noticeId, String username, String title, String deadline, String image) {
            this.noticeId = noticeId;
            this.username = username;
            this.title = title;
            this.deadline = deadline;
        }

        public void addSkill(SkillDTO sKillDTO) {
            skills.add(sKillDTO);
        }
    }

    @Data
    public static class DetailDTO {
        private Integer userId;
        private String username; //회사명
        private String address; //회사 주소
        private String birth; //설립일
        private String title; //공고제목
        private String deadline; //공고마감일
        private String type; //고용형태
        private String field; //분야
        private String content; //공고내용
        private String workPlace; //근무지
        private String email; //이메일
        private String tel; //연락처
        private Integer role; //역할
        private Integer noticeId;
        private Timestamp createdAt;

        private List<SkillDTO> skills = new ArrayList<>();

        public DetailDTO(Integer userId, String username, String address, String birth, String title, String deadline, String type, String field, String content, String workPlace, String email, String tel, Integer role, Integer noticeId, Timestamp createdAt) {
            this.userId = userId;
            this.username = username;
            this.address = address;
            this.birth = birth;
            this.title = title;
            this.deadline = deadline;
            this.type = type;
            this.field = field;
            this.content = content;
            this.workPlace = workPlace;
            this.email = email;
            this.tel = tel;
            this.role = role;
            this.noticeId = noticeId;
            this.createdAt = createdAt;
        }

        public void addSkill(SkillDTO skillDTO) {
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
package com.example.project1.apply;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApplyResponse {

    @AllArgsConstructor
    @Data
    public static class UserListDTO {
        private Integer resumeUserId; // 이력서 유저 번호
        private Integer noticeUserId; // 공고 유저 번호
        private Integer applyId; // 지원 번호
        private Integer noticeId; // 공고 번호
        private Integer resumeId; // 이력서 번호

        private String name; // 공고 올린 회사 이름
        private String title; // 공고 타이틀
        private String deadline; // 마감일
        private String type; // 고용 형태
        private Boolean pass; // 합격 여부
    }
}

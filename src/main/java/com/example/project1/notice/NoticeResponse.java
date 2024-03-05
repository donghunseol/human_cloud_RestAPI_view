package com.example.project1.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NoticeResponse {

    @NoArgsConstructor
    @AllArgsConstructor
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
    }

}
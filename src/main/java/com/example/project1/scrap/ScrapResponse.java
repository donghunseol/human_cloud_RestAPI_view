package com.example.project1.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapResponse {

    @AllArgsConstructor
    @Data
    public static class ScrapDTO{
        private Integer scrapId; // 스크랩 번호
        private Integer sUserId; // 스크랩 유저 번호
        private String username; // 스크랩 유저 이름
        private Integer nUserId; // 공고 주인 번호
        private String nName; // 공고 주인 이름
        private String content; // 공고 내용
        private String deadline; // 공고 마감일
        private String field; // 개발 분야
        private String title; // 공고 제목
        private String type; // 고용 형태
        private Integer role; // 구분
    }

    // 스크랩 버튼 활성화 DTO
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private Boolean isScrap;
    }
}
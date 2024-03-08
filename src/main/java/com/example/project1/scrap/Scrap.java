package com.example.project1.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "scrap_tb")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 스크랩 ID

    @Column(nullable = false)
    private Integer userId; // 스크랩한 유저 번호
    private Integer resumeId; // 이력서 번호 (null 허용)
    private Integer noticeId; // 공고 번호 (null 허용)

    @Column(nullable = false)
    private Integer role; // 기업인지 개인인지 구별 0, 1

    private Timestamp createdAt; // 생성 일자
}

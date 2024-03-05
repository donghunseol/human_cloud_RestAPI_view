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
    private Integer user_id; // 유저
    private Integer resume_id; // 이력서
    private Integer notice_id; // 공고

    @Column(nullable = false)
    private Integer role; // 기업인지 개인인지 구별 0, 1

    private Timestamp created_at;
}

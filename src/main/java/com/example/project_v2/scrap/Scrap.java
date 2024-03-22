package com.example.project_v2.scrap;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
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

    @CreationTimestamp
    private Timestamp createdAt; // 생성 일자

    @Builder
    public Scrap(Integer id, Integer userId, Integer resumeId, Integer noticeId, Integer role, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.resumeId = resumeId;
        this.noticeId = noticeId;
        this.role = role;
        this.createdAt = createdAt;
    }
}

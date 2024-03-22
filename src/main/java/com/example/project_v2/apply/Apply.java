package com.example.project_v2.apply;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "apply_tb")
@Data
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer resumeId; // 1

    @Column(nullable = false)
    private Integer noticeId; // 2

    // 개인->기업 (지원)

    private Boolean pass;

    private Timestamp createdAt;
}

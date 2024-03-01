package com.example.project1.notice;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "notice_tb")
@Data
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String field;

    private String workPlace;
    private String content;

    @Column(nullable = false)
    private Timestamp createdAt;
}

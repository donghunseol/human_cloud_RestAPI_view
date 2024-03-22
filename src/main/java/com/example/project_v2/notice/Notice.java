package com.example.project_v2.notice;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "notice_tb")
@Data
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn()
    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String field;

    private String workPlace;
    private String content;

    @Column(nullable = false)
    private String deadline;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Notice(Integer id, Integer userId, String title, String type, String field, String workPlace, String content, String deadline, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.type = type;
        this.field = field;
        this.workPlace = workPlace;
        this.content = content;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }
}
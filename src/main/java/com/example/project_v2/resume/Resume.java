package com.example.project_v2.resume;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "resume_tb")
@Data
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String title;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String license;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private String major;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Resume(Integer id, Integer userId, String title, String career, String license, String education, String major, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.career = career;
        this.license = license;
        this.education = education;
        this.major = major;
        this.createdAt = createdAt;
    }
}

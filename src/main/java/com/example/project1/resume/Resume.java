package com.example.project1.resume;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

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

    private Timestamp createdAt;
}

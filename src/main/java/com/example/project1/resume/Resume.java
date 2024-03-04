package com.example.project1.resume;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.sql.Timestamp;

@Table(name = "resume_tb")
@Data
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String license;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private String major;

    private Timestamp created_at;
}

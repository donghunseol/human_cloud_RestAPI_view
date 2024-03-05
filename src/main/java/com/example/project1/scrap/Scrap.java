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
    private Integer id;

    @Column(nullable = false)
    private Integer userId;
    private Integer resumeId;
    private Integer noticeId;

    @Column(nullable = false)
    private Integer role;

    private Timestamp createdAt;
}

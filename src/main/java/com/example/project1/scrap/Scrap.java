package com.example.project1.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "scrap_tb")
@Data
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer user_id;
    private Integer resume_id;
    private Integer notice_id;

    @Column(nullable = false)
    private Integer role;

    private Timestamp createdAt;
}

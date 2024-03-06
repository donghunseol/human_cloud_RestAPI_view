package com.example.project1.board;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "board_tb")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Timestamp created_at;
}

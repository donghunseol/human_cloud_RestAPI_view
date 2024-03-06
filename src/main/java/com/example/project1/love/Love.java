package com.example.project1.love;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "love_tb")
@Data
@Entity
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;
    private Integer boardId;

    private Timestamp createdAt;
}

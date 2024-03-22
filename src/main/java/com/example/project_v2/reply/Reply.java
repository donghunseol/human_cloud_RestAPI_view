package com.example.project_v2.reply;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "reply_tb")
@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Integer userId;
    private Integer boardId;

    private LocalDateTime createdAt;
}

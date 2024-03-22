package com.example.project_v2.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
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

    @CreationTimestamp
    private Timestamp created_at;

    @Builder
    public Board(Integer id, Integer userId, String title, String content, Timestamp created_at) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
    }
}

package com.example.project_v2.reply;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
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

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Reply(Integer id, String comment, Integer userId, Integer boardId, Timestamp createdAt) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.boardId = boardId;
        this.createdAt = createdAt;
    }
}

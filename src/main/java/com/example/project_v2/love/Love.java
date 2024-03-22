package com.example.project_v2.love;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name="love_tb", uniqueConstraints = {
        @UniqueConstraint(
                name="love_uk",
                columnNames={"board_id","user_id"}
        )})
@Data
@Entity
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer boardId;
    private Integer userId;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Love(Integer id, Integer boardId, Integer userId, Timestamp createdAt) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}

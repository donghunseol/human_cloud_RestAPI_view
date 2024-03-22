package com.example.project_v2.love;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

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


    private Timestamp createdAt;
}

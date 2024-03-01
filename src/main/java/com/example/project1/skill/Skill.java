package com.example.project1.skill;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "skill_tb")
@Data
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer resume_id;
    private Integer notice_id;

    @Column(nullable = false)
    private String name;

    private Integer role;
}

package com.example.project_v2.skill;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "skill_tb")
@Data
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer resumeId;
    private Integer noticeId;

    @Column(nullable = false)
    private String name;

    private Integer role;
}

package com.example.project_v2.skill;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

    @Builder
    public Skill(Integer id, Integer resumeId, Integer noticeId, String name, Integer role) {
        this.id = id;
        this.resumeId = resumeId;
        this.noticeId = noticeId;
        this.name = name;
        this.role = role;
    }
}

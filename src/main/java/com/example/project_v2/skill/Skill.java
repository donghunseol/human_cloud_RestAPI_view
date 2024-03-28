package com.example.project_v2.skill;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.resume.Resume;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"resume", "notice"})
@NoArgsConstructor
@Table(name = "skill_tb")
@Data
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @Column(nullable = false)
    private String name;

    private Integer role;

    @Builder
    public Skill(Integer id, Resume resume, Notice notice, String name, Integer role) {
        this.id = id;
        this.resume = resume;
        this.notice = notice;
        this.name = name;
        this.role = role;
    }
}
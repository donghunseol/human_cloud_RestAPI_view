package com.example.project_v2.notice;

import com.example.project_v2.skill.Skill;
import com.example.project_v2.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "notice_tb")
@Data
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn()
    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String field;

    private String workPlace;
    private String content;

    @Column(nullable = false)
    private String deadline;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "notice", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Skill> skills = new ArrayList<>();


    @Builder
    public Notice(Integer id, User user, Integer userId, String title, String type, String field, String workPlace, String content, String deadline, List<Skill> skills) {
        this.id = id;
        this.user = user;
        this.userId = userId;
        this.title = title;
        this.type = type;
        this.field = field;
        this.workPlace = workPlace;
        this.content = content;
        this.deadline = deadline;
        this.skills = skills;
    }
}
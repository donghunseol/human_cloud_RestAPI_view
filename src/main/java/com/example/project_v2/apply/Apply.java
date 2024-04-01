package com.example.project_v2.apply;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "apply_tb")
@Data
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "notice_fk", nullable = false)
    private Notice notice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "resume_fk", nullable = false)
    private Resume resume;

//    @Column(nullable = false)
//    private Integer resumeId; // 1
//
//    @Column(nullable = false)
//    private Integer noticeId; // 2

    // 개인->기업 (지원)

    private Boolean pass;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Apply(Integer id, User user, Notice notice, Resume resume, Boolean pass, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.notice = notice;
        this.resume = resume;
        this.pass = pass;
        this.createdAt = createdAt;
    }
}

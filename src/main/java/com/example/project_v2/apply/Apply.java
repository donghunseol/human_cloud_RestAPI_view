package com.example.project_v2.apply;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.resume.Resume;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Column(nullable = false)
    private Integer resumeId; // 1

    @Column(nullable = false)
    private Integer noticeId; // 2

    // 개인->기업 (지원)

    private Boolean pass;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Apply(Integer id, Integer resumeId, Integer noticeId, Boolean pass, Timestamp createdAt) {
        this.id = id;
        this.resumeId = resumeId;
        this.noticeId = noticeId;
        this.pass = pass;
        this.createdAt = createdAt;
    }
}

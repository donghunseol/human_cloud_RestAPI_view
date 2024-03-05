package com.example.project1.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "scrap_tb", uniqueConstraints = {
        @UniqueConstraint(
                name = "scrap_uk", // 유니크 이름 설정
                columnNames = {"user_id", "resume_id", "notice_id"} // 유니크 설정할 컬럼명
        )
})
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer user_id;
    private Integer resume_id;
    private Integer notice_id;

    @Column(nullable = false)
    private Integer role;

    private Timestamp created_at;
}

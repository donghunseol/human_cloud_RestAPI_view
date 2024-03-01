package com.example.project1.user;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "user_tb")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    private String image;

    @Column(nullable = false)
    private Integer role;

    @Column(nullable = false)
    private Timestamp createdAt;
}
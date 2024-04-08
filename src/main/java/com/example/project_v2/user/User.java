package com.example.project_v2.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "user_tb")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

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

    //ImageSave 필드값 사진이름 = imageName, 저장경로 = imageFileName 변경
    private String imageName; // 사진 이름
    private String imageFileName; // 저장경로

    @Column(nullable = false)
    private Integer role;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String username, String password, String name, String tel, String birth, String email, String address, String imageName, String imageFileName, Integer role, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.birth = birth;
        this.email = email;
        this.address = address;
        this.imageName = imageName;
        this.imageFileName = imageFileName;
        this.role = role;
        this.createdAt = createdAt;
    }

    //프로필 업데이트 setter
    public void setUpdateDTO(UserRequest.UpdateDTO reqDTO, String imageFileName) {
        this.username = reqDTO.getUsername();
        this.password = reqDTO.getPassword();
        this.tel = reqDTO.getTel();
        this.email = reqDTO.getEmail();
        this.address = reqDTO.getAddress();
        this.imageName = reqDTO.getImageName();
        this.imageFileName = imageFileName;
    }
}
package com.example.project_v2.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String name;
    private String tel;
    private String birth;
    private String email;
    private String address;
    private String Name;
    private String imageName;  // 사진 이름
    private String imageFileName; // 저장경로
    private Integer role;
    private boolean isLoginUser;
    private Timestamp createdAt;

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.tel = user.getTel();
        this.birth = user.getBirth();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.imageName = user.getImageName();
        this.imageFileName = user.getImageFileName();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.isLoginUser = false;
        // true 면 기업, false 면 개인
        if (user.getRole() == 1) {
            isLoginUser = true;
        }
    }

    public static User toEntity(SessionUser sessionUser) {
        User user = new User();
        user.setId(sessionUser.getId());
        user.setUsername(sessionUser.getUsername());
        user.setName(sessionUser.getName());
        user.setTel(sessionUser.getTel());
        user.setBirth(sessionUser.getBirth());
        user.setEmail(sessionUser.getEmail());
        user.setAddress(sessionUser.getAddress());
        user.setImageName(sessionUser.getImageName());
        user.setImageFileName(sessionUser.getImageFileName());
        user.setRole(sessionUser.getRole());
        user.setCreatedAt(sessionUser.getCreatedAt());
        return user;
    }
}
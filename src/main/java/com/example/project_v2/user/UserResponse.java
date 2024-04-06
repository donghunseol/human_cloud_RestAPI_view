package com.example.project_v2.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

public class UserResponse {
    @Data
    public static class DTO {
        private Integer id;
        private String username;
        private String name;
        private String tel;
        private String birth;
        private String email;
        private String address;
        private String encodedData; // base64 저장경로
        private Integer role;
        private Timestamp createdAt;

        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.tel = user.getTel();
            this.birth = user.getBirth();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.encodedData = user.getEncodedData();
            this.role = user.getRole();
            this.createdAt = user.getCreatedAt();
        }
    }
}
package com.example.project1.user;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.springframework.stereotype.Repository;

public class UserRequest {
    @Data
    public static class JoinDTO{
        private String username;
        private String password;
        private String name;
        private String tel;
        private String birth;
        private String email;
        private String address;
        private Integer role;
    }
}

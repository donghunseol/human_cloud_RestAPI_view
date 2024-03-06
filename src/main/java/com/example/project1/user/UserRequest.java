package com.example.project1.user;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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

    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }

    @Data
    public static class UpdateDTO{
        private String username;
        private String password;
        private String name;
        private String tel;
        private String birth;
        private String email;
        private String address;
        private String  image;
        private Integer role;



    }
}

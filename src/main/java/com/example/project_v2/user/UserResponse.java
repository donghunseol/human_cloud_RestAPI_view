package com.example.project_v2.user;

import lombok.Data;

public class UserResponse {
    @Data
    public static class DTO{
        private Integer id;
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

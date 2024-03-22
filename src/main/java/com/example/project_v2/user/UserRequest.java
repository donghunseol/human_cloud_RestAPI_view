package com.example.project_v2.user;

import lombok.Data;

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
    public static class UpdateNoticeDTO{
        private String username;
        private String birth;
        private String address;
        private String email;
        private String tel;
    }
  
    @Data
    public static class LoginDTO{
        private Integer id;
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

    public static class DeleteDTO{
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

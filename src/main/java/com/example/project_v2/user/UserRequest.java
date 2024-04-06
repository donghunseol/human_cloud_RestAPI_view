package com.example.project_v2.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class UserRequest {

    @Data
    public static class JoinDTO{
        private Integer id; // 유저 번호 (PK)
        private String username; // 로그인 ID
        private String password; // 비밀번호
        private String name; // 유저 성명
        private String tel; // 전화번호
        private String birth; // 생년월일
        private String email; // 이메일
        private String address; // 주소
        private Integer role; // 개인, 기업 구분

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .tel(tel)
                    .birth(birth)
                    .email(email)
                    .address(address)
                    .role(role)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private Integer id; // 유저 번호 (PK)
        private String username; // 로그인 ID
        private String password; // 비밀번호
    }

    @Data
    public static class UpdateDTO {
        private String username; // 로그인 ID
        private String password; // 비밀번호
        private String tel; // 전화번호
        private String email; // 이메일
        private String address; // 주소
        private String imageName; // 사진 이름
        private String encodedData; // base64 저장경로

        public UpdateDTO(String username, String password, String tel, String email, String address, String imageName, String encodedData) {
            this.username = username;
            this.password = password;
            this.tel = tel;
            this.email = email;
            this.address = address;
            this.imageName = imageName;
            this.encodedData = encodedData;
        }
    }
}

package com.example.project_v2.scrap;

import com.example.project_v2.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ScrapRequest {

    @NoArgsConstructor
    @Data
    public static class SaveDTO {
        private Integer id;
        private Integer userId;
        private Integer resumeId;
        private Integer noticeId;
        private Integer role;

        public Scrap toEntity(User sessionUser, Integer id) {
            if(sessionUser.getRole()==0){
                return Scrap.builder()
                        .userId(sessionUser.getId())
                        .noticeId(id)
                        .role(sessionUser.getRole())
                        .build();
            }else {
                return Scrap.builder()
                        .userId(sessionUser.getId())
                        .resumeId(id)
                        .role(sessionUser.getRole())
                        .build();
            }
        }
    }
}

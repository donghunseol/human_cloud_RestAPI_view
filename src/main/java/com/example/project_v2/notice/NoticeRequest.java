package com.example.project_v2.notice;

import com.example.project_v2.skill.Skill;
import com.example.project_v2.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class NoticeRequest {

    @Data
    public static class SaveDTO{
        private Integer id; // 공고 번호 (pk)
        private User user;
        private String title; // 공고 제목
        private String type; // 고용 형태
        private String field; // 분야
        private String workPlace; // 근무지
        private String deadline; // 마감일
        private String content; // 상세 제목
        private List<SkillDTO> skills = new ArrayList<>();

//        @Data
//        public static class UserDTO{
//            private Integer id;
//            private String name;
//            private String email;
//            private String tel;
//            private String address;
//            private String birth;
//
//            public User toEntity(){
//                return User.builder()
//                        .id(id)
//                        .name(name)
//                        .email(email)
//                        .tel(tel)
//                        .address(address)
//                        .birth(birth)
//                        .build();
//            }
//        }

        @Data
        public static class SkillDTO{
            private Integer id;
            private Notice notice;
            private String name;
            private Integer role;

            public Skill toEntity(){
                return Skill.builder()
                        .id(id)
                        .notice(notice)
                        .name(name)
                        .role(role)
                        .build();
            }
        }

        public Notice toEntity(User sessionUser){
            List<Skill> skillEntities = this.skills.stream()
                    .map(SkillDTO::toEntity) // SkillDTO를 Skill 엔티티로 변환
                    .toList();

            return Notice.builder()
                    .user(sessionUser)
                    .title(title)
                    .type(type)
                    .field(field)
                    .workPlace(workPlace)
                    .deadline(deadline)
                    .content(content)
                    .skills(skillEntities)
                    .build();
        }
    }
}

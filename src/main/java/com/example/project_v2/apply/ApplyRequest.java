package com.example.project_v2.apply;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.user.User;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

public class ApplyRequest {

    @Data
    public static class SaveDTO{
        private Integer id;
        private Integer noticeId;
        private Integer resumeId;

        public Apply toEntity(User user, Notice notice, Resume resume){
            return Apply.builder()
                    .user(user)
                    .notice(notice)
                    .resume(resume)
                    .build();
        }
    }
}

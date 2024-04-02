package com.example.project_v2.scrap;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

public class ScrapResponse {

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL) // null이 뜨는 json은 출력안하게 하는 어노테이션
    public static class DTO {
        private Integer id;
        private Integer userId;
        private Integer resumeId;
        private Integer noticeId;
        private Timestamp createdAt;

        public DTO(Scrap scrap) {
            this.id = scrap.getId();
            this.userId = scrap.getUser().getId();

            if (scrap.getUser().getRole() == 1) {
                this.resumeId = scrap.getResume().getId();
            } else {
                this.noticeId = scrap.getNotice().getId();
            }
            this.createdAt = scrap.getCreatedAt();
        }
    }
}
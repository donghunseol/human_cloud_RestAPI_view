package com.example.project_v2.notice;

import lombok.Data;

public class NoticeRequest {

    @Data
    public static class SaveDTO{
        private Integer id;
        private Integer userId;
        private String title;
        private String type;
        private String field;
        private String workPlace;
        private String deadline;
        private String content;

        public Notice toEntity(){
            return Notice.builder()
                    .title(title)
                    .type(type)
                    .field(field)
                    .workPlace(workPlace)
                    .deadline(deadline)
                    .content(content)
                    .build();
        }
    }
}

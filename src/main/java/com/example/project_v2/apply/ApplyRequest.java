package com.example.project_v2.apply;

import lombok.Data;

public class ApplyRequest {

    @Data
    public static class SaveDTO{
        private Integer id;
        private Integer noticeId;
        private Integer resumeId;
    }
}

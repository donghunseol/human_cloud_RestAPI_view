package com.example.project1.reply;

import lombok.Data;

public class ReplyRequest {
    @Data
    public static class WriteDTO {
        private String comment;
        private Integer boardId;
    }
}

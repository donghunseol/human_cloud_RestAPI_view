package com.example.project1.board;

import com.example.project1.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DetailDTO{
        // FLAT한 데이터
        private int id;
        private String title;
        private String content;
        private int userId; // 게시글 작성자 ID
        private String username;
        private Boolean boardOwner;

        public void isBoardOwner(User sessionUser){ // int나 double은 값이 안들어가면 null이 나온다
            if (sessionUser == null) boardOwner = false;
            else boardOwner = sessionUser.getId() == userId;
        }
    }
    @AllArgsConstructor
    @Data
    public static class ReplyDTO{
        private Integer id;
        private Integer userId;
        private String comment;
        private String username;
        private Boolean replyOwner;

        public ReplyDTO(Object[] ob, User sessionUser) {
            this.id = (Integer) ob[0];
            this.userId = (Integer) ob[1];;
            this.comment = (String) ob[2];;
            this.username = (String) ob[3];;

            if (sessionUser == null){
                replyOwner = false;
            }else {
                replyOwner = sessionUser.getId() == userId;
            }

        }
    }


    @Data
    public static class BoardDTO {
        private Integer id;
        private String title;
        private Boolean first;
        private Boolean last;
        private Integer prev;
        private Integer next;

        public BoardDTO(Integer id, String title, Boolean first, Boolean last, Integer prev, Integer next) {
            this.id = id;
            this.title = title;
            this.first = first;
            this.last = last;
            this.prev = prev;
            this.next = next;
        }
    }
}

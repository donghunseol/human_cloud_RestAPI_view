package com.example.project1.reply;

import com.example.project1.board.BoardResponse;
import com.example.project1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    // 부분 조회
    public void findById(){

    }

    // 전체 조회
    public List<BoardResponse.ReplyDTO> findByBoardId(int boardId , User sessionUser){
        String q = """
                select rt.id, rt.user_id, rt.comment, ut.username from reply_tb rt inner join user_tb ut on rt.user_id = ut.id where rt.board_id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, boardId);

        List<Object[]> rows = query.getResultList();

        return rows.stream().map(row -> new BoardResponse.ReplyDTO(row, sessionUser)).toList();
    }

        // 저장
        @Transactional //둘이 동시에 write 불가
        public void save(ReplyRequest.WriteDTO requestDTO, int userId) {
            Query query = em.createNativeQuery("insert into reply_tb(comment, board_id, user_id, created_at) values(?,?,?,now())");
            query.setParameter(1, requestDTO.getComment());
            query.setParameter(2, requestDTO.getBoardId());
            query.setParameter(3, userId);

            query.executeUpdate();
        }



    // 삭제
    @Transactional
    public void deleteById(){

    }

    // 수정
    @Transactional
    public void update(){

    }
    }

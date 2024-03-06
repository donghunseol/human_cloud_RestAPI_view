package com.example.project1.reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    // 부분 조회
    public void findById(){

    }

    // 전체 조회
    public void findAll(){

    }

    // 저장
    @Transactional
    public void save(ReplyRequest.WriteDTO requestDTO, Integer userId){
        Query query = em.createNativeQuery("insert into reply_tb(user_id,board_id, comment, created_at) values (?,?,?, now())");
        query.setParameter(1, userId);
        query.setParameter(2, requestDTO.getBoardId());
        query.setParameter(3, requestDTO.getComment());

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

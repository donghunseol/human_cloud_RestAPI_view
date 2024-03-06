package com.example.project1.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    // 부분 조회
    public void findById(Integer id){
        String q = "select * from apply_tb where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // 전체 조회
    public void findAll(){
        String q = "select * from apply_tb";
        Query query = em.createNativeQuery(q);
        query.executeUpdate();
    }

    // 저장
    @Transactional
    public void save(ApplyRequest.SaveDTO requestDTO){
        String q = "insert into apply_tb (resume_id, notice_id, created_at) values (?, ?, now())";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getNoticeId());
        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(Integer id){
        String q = "delete from apply_tb where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // 수정
    @Transactional
    public void update(){

    }
}

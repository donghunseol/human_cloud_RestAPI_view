package com.example.project1.notice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Repository
public class NoticeRepository {

    private final EntityManager em;

    // 부분 조회
    public Notice findById(int id){
        Query query = em.createNativeQuery("select * from notice_tb where id=?", Notice.class);
        query.setParameter(1, id);

        Notice notice = (Notice) query.getSingleResult();
        return notice;
    }

    // 전체 조회
    public List<Notice> findAll(){
        Query query = em.createNativeQuery("select * from notice_tb order by id desc", Notice.class);
        return query.getResultList();
    }

    // 저장
    @Transactional
    public void save(NoticeRequest.SaveDTO requestDTO, int userId){
        Query query = em.createNativeQuery("insert into notice_tb(user_id, title, type, field, work_place, content, deadline, created_at) values (?, ?, ?, ?, ?, ?, ?, now())");
        query.setParameter(1, userId);
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getType());
        query.setParameter(4, requestDTO.getField());
        query.setParameter(5, requestDTO.getWork_place());
        query.setParameter(6, requestDTO.getContent());
        query.setParameter(7, requestDTO.getDeadline());

        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(int id){
        Query query = em.createNativeQuery("delete from notice_tb where id=?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // 수정
    @Transactional
    public void update(){

    }
}
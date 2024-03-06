package com.example.project1.notice;

import com.example.project1.user.UserRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalTime.now;

@Component
@RequiredArgsConstructor
@Repository
public class NoticeRepository {

    private final EntityManager em;

    // 부분 조회
    public Notice findById(int id){
        Query query = em.createNativeQuery("select * from notice_tb where id=?;", Notice.class);
        query.setParameter(1, id);

        Notice notice = (Notice) query.getSingleResult();
        return notice;
    }

    // 전체 조회
    public List<Notice> findAll(){
        Query query = em.createNativeQuery("select * from notice_tb order by id desc;", Notice.class);
        return query.getResultList();
    }

    public NoticeResponse.DetailDTO findNoticeById(int id){
        Query query = em.createNativeQuery("select ut.id, ut.username, ut.address, ut.birth, nt.title, nt.deadline, nt.type, nt.field, nt.content, nt.work_place, ut.email, ut.tel, ut.role, nt.id, nt.created_at from notice_tb nt left outer join user_tb ut on ut.id = nt.user_id where ut.role=1 and nt.id=?;");
        query.setParameter(1,id);

        JpaResultMapper rm = new JpaResultMapper();
        NoticeResponse.DetailDTO responseDTO = rm.uniqueResult(query, NoticeResponse.DetailDTO.class);
        return responseDTO;
    }

    // 저장
    @Transactional
    public void save(NoticeRequest.SaveDTO requestDTO, int userId){
        Query query = em.createNativeQuery("insert into notice_tb(user_id, title, type, field, work_place, content, deadline, created_at) values (?, ?, ?, ?, ?, ?, ?, now());");
        query.setParameter(1, userId);
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getType());
        query.setParameter(4, requestDTO.getField());
        query.setParameter(5, requestDTO.getWorkPlace());
        query.setParameter(6, requestDTO.getContent());
        query.setParameter(7, requestDTO.getDeadline());


        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(Integer id){
        Query query = em.createNativeQuery("delete from notice_tb where id=?;");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // 수정
    @Transactional
    public void update(NoticeRequest.UpdateDTO updateDTO_n, UserRequest.UpdateNoticeDTO updateDTO_u, int id){
        Query query1 = em.createNativeQuery("UPDATE user_tb SET username = ?, address = ?, birth = ? , email=?, tel = ? WHERE id IN (SELECT ut.id FROM notice_tb nt LEFT OUTER JOIN user_tb ut ON ut.id = nt.user_id WHERE ut.role = 1 AND nt.id = ?);");
        query1.setParameter(1, updateDTO_u.getUsername());
        query1.setParameter(2, updateDTO_u.getAddress());
        query1.setParameter(3, updateDTO_u.getBirth());
        query1.setParameter(4, updateDTO_u.getEmail());
        query1.setParameter(5, updateDTO_u.getTel());
        query1.setParameter(6, id);

        Query query2 = em.createNativeQuery("UPDATE notice_tb SET title = ?, deadline = ?, type = ?, field = ?, content = ?, work_place = ?, created_at=? WHERE id = ?;");
        query2.setParameter(1, updateDTO_n.getTitle());
        query2.setParameter(2, updateDTO_n.getDeadline());
        query2.setParameter(3, updateDTO_n.getType());
        query2.setParameter(4, updateDTO_n.getField());
        query2.setParameter(5, updateDTO_n.getContent());
        query2.setParameter(6, updateDTO_n.getWorkPlace());
        query2.setParameter(7, now());
        query2.setParameter(8, id);

        query1.executeUpdate();
        query2.executeUpdate();
    }
}
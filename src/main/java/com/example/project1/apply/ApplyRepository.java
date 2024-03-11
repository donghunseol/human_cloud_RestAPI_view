package com.example.project1.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    // 지원자 입장 지원 공고 리스트 출력
    public List<ApplyResponse.UserListDTO> findUserApplyById(Integer id) {
        String q = """
                SELECT rt.user_id resume_user_id, nt.user_id notice_user_id, at.id apply_id, at.notice_id, at.resume_id,
                ut.name, nt.title, nt.deadline, nt.type, at.pass FROM apply_tb at
                LEFT OUTER JOIN notice_tb nt
                ON at.notice_id = nt.id
                LEFT OUTER JOIN resume_tb rt
                ON rt.id = at.resume_id
                LEFT OUTER JOIN user_tb ut
                ON nt.user_id = ut.id
                WHERE rt.user_id = ?;
                """;
        Query query = em.createNativeQuery(q, ApplyResponse.UserListDTO.class);
        query.setParameter(1, id);

        return query.getResultList();
    }

    public ApplyResponse.UserListDTO findPassById(Integer id) {
        String q = "select * from apply_tb where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        return (ApplyResponse.UserListDTO) query.getSingleResult();
    }

    // 부분 조회
    public Apply findById(Integer id) {
        String q = "select * from apply_tb where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        return (Apply) query.getSingleResult();
    }

    // 전체 조회
    public List<Apply> findAll() {
        String q = "select * from apply_tb";
        Query query = em.createNativeQuery(q);

        return query.getResultList();
    }

    // 저장
    @Transactional
    public void save(ApplyRequest.SaveDTO requestDTO) {
        String q = "insert into apply_tb (resume_id, notice_id, created_at) values (?, ?, now())";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getNoticeId());

        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(Integer id) {
        String q = "delete from apply_tb where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }
}

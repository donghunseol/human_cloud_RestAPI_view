package com.example.project1.scrap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScrapRepository {
    private final EntityManager em;

    // 스크랩 여부와 무엇을 스크랩했는지 확인하는 코드
    public List<ScrapResponse.ScrapDTO> findById(int userId) {
        String q = """
                SELECT sut.scrap_id, sut.s_user_id, sut.username, nt.user_id AS n_user_id, ut2.name AS n_name, nt.content, nt.deadline, nt.field, nt.title, nt.type, sut.role
                FROM (
                    SELECT st.id AS scrap_id, st.user_id AS s_user_id, st.notice_id, ut.role, ut.username
                    FROM scrap_tb st
                    LEFT OUTER JOIN user_tb ut ON ut.id = st.user_id
                    WHERE ut.role = 0
                ) sut
                LEFT OUTER JOIN notice_tb nt ON sut.notice_id = nt.id
                LEFT OUTER JOIN user_tb ut2 ON nt.user_id = ut2.id
                WHERE s_user_id = ?;
                """;
        Query query = em.createNativeQuery(q, ScrapResponse.ScrapDTO.class);
        query.setParameter(1, userId);

        return query.getResultList();
    }

    // 부분 조회
    public void findById() {

    }

    // 전체 조회
    public void findAll() {

    }

    // 개인 회원 이력서 값 저장
    @Transactional
    public void individualSave(ScrapRequest.IndividualDTO requestDTO) {
        String q = "insert into scrap_tb (user_id, notice_id, role, created_at) value(?, ?, ?, now())";
        Query query = em.createNamedQuery(q, Scrap.class);
        query.setParameter(1, requestDTO.getUserId());
        query.setParameter(2, requestDTO.getNoticeId());
        query.setParameter(3, requestDTO.getRole());
        query.executeUpdate();
    }

    @Transactional
    public void companySave(ScrapRequest.CompanyDTO requestDTO) {
        String q = "insert into scrap_tb (user_id, resume_id, role, created_at) value(?, ?, ?, now())";
        Query query = em.createNamedQuery(q, Scrap.class);
        query.setParameter(1, requestDTO.getUserId());
        query.setParameter(2, requestDTO.getResumeId());
        query.setParameter(3, requestDTO.getRole());
        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(Integer id) {
        String q = "delete from scrap_tb where id=?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }
}

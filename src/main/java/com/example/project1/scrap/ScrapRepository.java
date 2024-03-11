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

    public Scrap findById(Integer userId){
        String q = "SELECT * FROM scrap_tb WHERE id = ?";
        Query query = em.createNativeQuery(q, Scrap.class);
        query.setParameter(1, userId);

        Scrap scrap = (Scrap) query.getSingleResult();
        return scrap;
    }

    // 스크랩 여부와 무엇을 스크랩했는지 확인하는 코드
    public List<ScrapResponse.ScrapDTO> findByIdList(Integer userId, Integer sessionUserRole) {
        String q = """
                SELECT sut.scrap_id, sut.s_user_id, sut.username, nt.user_id AS n_user_id, ut2.name AS n_name, nt.content, nt.deadline, nt.field, nt.title, nt.type, sut.role
                FROM (
                    SELECT st.id AS scrap_id, st.user_id AS s_user_id, st.notice_id, ut.role, ut.username
                    FROM scrap_tb st
                    LEFT OUTER JOIN user_tb ut ON ut.id = st.user_id
                    WHERE ut.role = ?
                ) sut
                LEFT OUTER JOIN notice_tb nt ON sut.notice_id = nt.id
                LEFT OUTER JOIN user_tb ut2 ON nt.user_id = ut2.id
                WHERE s_user_id = ?;
                """;
        Query query = em.createNativeQuery(q, ScrapResponse.ScrapDTO.class);
        query.setParameter(1, sessionUserRole);
        query.setParameter(2, userId);

        return query.getResultList();
    }

    // 개인 회원 공고 정보 저장
    @Transactional
    public Scrap userSave(Integer sessionUserId, ScrapRequest.UserDTO requestDTO) {
        String q1 = "insert into scrap_tb (user_id, notice_id, role, created_at) values (?, ?, 1, now())";
        Query query1 = em.createNativeQuery(q1, Scrap.class);
        query1.setParameter(1, sessionUserId);
        query1.setParameter(2, requestDTO.getNoticeId());
        // query1.setParameter(3, requestDTO.getRole());
        query1.executeUpdate();

        String q2 = "SELECT * FROM scrap_tb WHERE id = (SELECT max(id) FROM scrap_tb)";
        Query query2= em.createNativeQuery(q2, Scrap.class);

        Scrap scrap = (Scrap) query2.getSingleResult();

        return scrap;
    }

    // 기업 회원 이력서 정보 저장
    @Transactional
    public Scrap companySave(Integer sessionUserId, ScrapRequest.CompanyDTO requestDTO) {
        String q = "insert into scrap_tb (user_id, resume_id, role, created_at) values (?, ?, ?, now())";
        Query query = em.createNativeQuery(q, Scrap.class);
        query.setParameter(1, sessionUserId);
        query.setParameter(2, requestDTO.getResumeId());
        query.setParameter(3, requestDTO.getRole());
        query.executeUpdate();

        String q2 = "SELECT * FROM scrap_tb WHERE id = (SELECT max(id) FROM scrap_tb)";
        Query query2= em.createNativeQuery(q2, Scrap.class);

        Scrap scrap = (Scrap) query2.getSingleResult();

        return scrap;
    }

    // 삭제
    @Transactional
    public void deleteById(Integer id) {
        String q = "delete from scrap_tb where id=?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // 로그인 안했을때 안됨.
    public ScrapResponse.DetailDTO findScrap(Integer noticeId){
        Integer id = 0;
        Boolean isScrap = false;

        System.out.println("id : " + id);
        System.out.println("isScrap : " + isScrap);

        ScrapResponse.DetailDTO responseDTO = new ScrapResponse.DetailDTO(id, isScrap);
        return responseDTO;
    }

    // 로그인했다!
    public ScrapResponse.DetailDTO findScrap(Integer noticeId, Integer sessionUserId){
        String q = """
                SELECT
                    id,
                    CASE
                        WHEN notice_id IS NULL THEN FALSE
                        ELSE TRUE
                    END AS isScrap
                FROM
                    scrap_tb
                WHERE
                    user_id = ? AND notice_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        query.setParameter(2, noticeId);

        System.out.println("sessionUserId : " + sessionUserId);
        System.out.println("noticeId : " + noticeId);

        Integer id = null;
        Boolean isScrap = null;
        Object[] row = (Object[]) query.getSingleResult();

        try {
            System.out.println("트라이임");
            id = (Integer) row[0];
            isScrap = (Boolean) row[1];
        } catch (Exception e) {
            System.out.println("캐치임");
            id = 0;
            isScrap = false;
        }

        ScrapResponse.DetailDTO responseDTO = new ScrapResponse.DetailDTO(id, isScrap);
        return responseDTO;
    }
}

package com.example.project1.notice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Repository
public class NoticeRepository {

    private final EntityManager em;

    // 부분 조회
    public Notice findById(Integer id) {
        Query query = em.createNativeQuery("select * from notice_tb where id=?;", Notice.class);
        query.setParameter(1, id);

        Notice notice = (Notice) query.getSingleResult();
        return notice;
    }

    // 전체 조회
    public List<NoticeResponse.DTO> findSearchAll(String keyword) {
        String sql = """
                    select n.id, u.username, n.title, n.deadline
                    from notice_tb n
                    left outer join user_tb u on u.id = n.user_id
                    where s.name like ? 
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, "%" + keyword + "%");

        List<Object[]> rows = query.getResultList();
        Map<Integer, NoticeResponse.DTO> noticeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            String username = (String) row[1];
            String title = (String) row[2];
            String deadline = (String) row[3];
            String image = (String) row[4];

            NoticeResponse.DTO notice = noticeMap.get(id);
            if (notice == null) {
                notice = new NoticeResponse.DTO(
                        id, username, title, deadline, image
                );
                noticeMap.put(id, notice);
            }

            Integer skillId = (Integer) row[5];
            String skillName = (String) row[6];
            if (skillId != null && skillName != null) {
                NoticeResponse.SKillDTO skill = new NoticeResponse.SKillDTO(
                        skillId, skillName
                );
                notice.addSkill(skill);
            }
        }

        return new ArrayList<>(noticeMap.values());
    }

    // 전체 조회
    public List<NoticeResponse.DTO> findAll() {
        String sql = """
                    select n.id, u.username, n.title, n.deadline, u.image
                    from notice_tb n
                    left outer join user_tb u on u.id = n.user_id
                """;
        Query query = em.createNativeQuery(sql);

        List<Object[]> rows = query.getResultList();
        Map<Integer, NoticeResponse.DTO> noticeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            String username = (String) row[1];
            String title = (String) row[2];
            String deadline = (String) row[3];
            String image = (String) row[4];

            NoticeResponse.DTO notice = noticeMap.get(id);
            if (notice == null) {
                notice = new NoticeResponse.DTO(
                        id, username, title, deadline, image
                );
                noticeMap.put(id, notice);
            }
        }

        return new ArrayList<>(noticeMap.values());
    }

    // 상세 조회(해당 유저가 선택한 공고)
    public NoticeResponse.DetailDTO findNoticeById(Integer nId) {
        String sql = """
                    select u.id user_id, u.username, u.address, u.birth, n.title, n.deadline, n.type, n.field, n.content, n.work_place, u.email, u.tel, u.role, n.id notice_id, n.created_at, s.id skill_id, s.name
                    from notice_tb n
                    left outer join skill_tb s on n.id = s.notice_id 
                    left outer join user_tb u on u.id = n.user_id
                    where n.id=?;
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, nId);

        List<Object[]> rows = (List<Object[]>) query.getResultList();

        Object[] noticeOb = rows.getFirst();

        Integer userId = (Integer) noticeOb[0];
        String username = (String) noticeOb[1];
        String address = (String) noticeOb[2];
        String birth = (String) noticeOb[3];
        String title = (String) noticeOb[4];
        String deadline = (String) noticeOb[5];
        String type = (String) noticeOb[6];
        String field = (String) noticeOb[7];
        String content = (String) noticeOb[8];
        String workPlace = (String) noticeOb[9];
        String email = (String) noticeOb[10];
        String tel = (String) noticeOb[11];
        Integer role = (Integer) noticeOb[12];
        Integer noticeId = (Integer) noticeOb[13];
        Timestamp createdAt = (Timestamp) noticeOb[14];

        NoticeResponse.DetailDTO notice = new NoticeResponse.DetailDTO(
                userId, username, address, birth, title, deadline, type, field, content, workPlace, email, tel, role, noticeId, createdAt
        );

        for (Object[] row : rows) {
            Integer skillId = (Integer) row[15];
            String skillName = (String) row[16];

            NoticeResponse.SKillDTO skill = new NoticeResponse.SKillDTO(
                    skillId, skillName
            );

            if (nId != null) notice.addSkill(skill);
        }

        return notice;
    }

    @Transactional
    public void save(Integer userId, NoticeRequest.NoticeDTO notice, List<String> skillNames) {
        String noticeSql = """
                insert into notice_tb(user_id, title, type, field, work_place, content, deadline, created_at) 
                values (?, ?, ?, ?, ?, ?, ?, now());
                """;
        Query noticeQuery = em.createNativeQuery(noticeSql);
        noticeQuery.setParameter(1, userId);
        noticeQuery.setParameter(2, notice.getTitle());
        noticeQuery.setParameter(3, notice.getType());
        noticeQuery.setParameter(4, notice.getField());
        noticeQuery.setParameter(5, notice.getWorkPlace());
        noticeQuery.setParameter(6, notice.getContent());
        noticeQuery.setParameter(7, notice.getDeadline());
        noticeQuery.executeUpdate();

        String findNoticeIdSql = "SELECT LAST_INSERT_ID()";
        Query findNoticeIdQuery = em.createNativeQuery(findNoticeIdSql);
        Long noticeId = (Long) findNoticeIdQuery.getSingleResult();

        for (String skillName : skillNames) {
            String skillSql = """
                    INSERT INTO skill_tb (resume_id, notice_id, name, role)
                    VALUES (?,?,?,?)
                    """;
            Query skillQuery = em.createNativeQuery(skillSql);
            skillQuery.setParameter(1, null);
            skillQuery.setParameter(2, noticeId);
            skillQuery.setParameter(3, skillName);
            skillQuery.setParameter(4, 1);
            skillQuery.executeUpdate();
        }
    }

    // 삭제
    @Transactional
    public void deleteById(Integer noticeId) {
        String skillDeleteSql = """
                delete from skill_tb where notice_id =?
                """;
        Query skillDelete = em.createNativeQuery(skillDeleteSql);
        skillDelete.setParameter(1, noticeId);
        skillDelete.executeUpdate();

        String noticeDeleteSql = """
                delete from notice_tb where id = ?
                """;
        Query noticeDelete = em.createNativeQuery(noticeDeleteSql);
        noticeDelete.setParameter(1, noticeId);
        noticeDelete.executeUpdate();
    }

    // 수정
    @Transactional
    public void update(Integer noticeId, NoticeRequest.NoticeDTO notice, List<String> skillNames) {
        String skillDeleteSql = """
                delete from skill_tb where notice_id =?
                """;
        Query skillDelete = em.createNativeQuery(skillDeleteSql);
        skillDelete.setParameter(1, noticeId);
        skillDelete.executeUpdate();

        String noticeSql = """
                update notice_tb set title = ?, type = ?, field = ?, work_place = ?, deadline = ? , content = ?
                where id = ?
                """;
        Query resumeQuery = em.createNativeQuery(noticeSql);
        resumeQuery.setParameter(1, notice.getTitle());
        resumeQuery.setParameter(2, notice.getType());
        resumeQuery.setParameter(3, notice.getField());
        resumeQuery.setParameter(4, notice.getWorkPlace());
        resumeQuery.setParameter(5, notice.getDeadline());
        resumeQuery.setParameter(6, notice.getContent());
        resumeQuery.setParameter(7, noticeId);
        resumeQuery.executeUpdate();

        for (String skillName : skillNames) {
            String skillSql = """
                    INSERT INTO skill_tb (resume_id, notice_id, name, role)
                    VALUES (?,?,?,?)
                    """;
            Query skillQuery = em.createNativeQuery(skillSql);
            skillQuery.setParameter(1, null);
            skillQuery.setParameter(2, noticeId);
            skillQuery.setParameter(3, skillName);
            skillQuery.setParameter(4, 1);
            skillQuery.executeUpdate();
        }

    }
}
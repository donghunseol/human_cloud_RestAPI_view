package com.example.project1.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {
    private final EntityManager em;

    // 전체 검색 조회(검색한 키워드의 모든 유저가 작성된 이력서)
    public List<ResumeResponse.DTO> findSearchAll(String keyword) {
        String sql = """
                select r.id resume_id, u.id user_id, r.title, u.name, u.image, s.id, s.name, 
                from resume_tb r 
                left outer join skill_tb s on r.id = s.resume_id 
                left outer join user_tb u on u.id = r.user_id 
                where s.name like ? 
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, "%" + keyword + "%");

        List<Object[]> rows = query.getResultList();
        Map<Integer, ResumeResponse.DTO> resumeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            Integer userId = (Integer) row[1];
            String title = (String) row[2];
            String name = (String) row[3];
            String image = (String) row[4];

            ResumeResponse.DTO resume = resumeMap.get(id);
            if (resume == null) {
                resume = new ResumeResponse.DTO(
                        id, userId, title, name, image
                );
                resumeMap.put(id, resume);
            }

            Integer skillId = (Integer) row[5];
            String skillName = (String) row[6];
            if (skillId != null && skillName != null) {
                ResumeResponse.SkillDTO skill = new ResumeResponse.SkillDTO(
                        skillId, skillName
                );
                resume.addSkill(skill);
            }
        }

        return new ArrayList<>(resumeMap.values());
    }

    // 전체 조회(모든 유저가 작성된 이력서)
    public List<ResumeResponse.DTO> findAll() {
        String sql = """
                select r.id resume_id, u.id user_id, r.title, u.name, u.image, s.id, s.name, 
                from resume_tb r 
                left outer join skill_tb s on r.id = s.resume_id 
                left outer join user_tb u on u.id = r.user_id
                """;
        Query query = em.createNativeQuery(sql);

        List<Object[]> rows = query.getResultList();
        Map<Integer, ResumeResponse.DTO> resumeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            Integer userId = (Integer) row[1];
            String title = (String) row[2];
            String name = (String) row[3];
            String image = (String) row[4];

            ResumeResponse.DTO resume = resumeMap.get(id);
            if (resume == null) {
                resume = new ResumeResponse.DTO(
                        id, userId, title, name, image
                );
                resumeMap.put(id, resume);
            }

            Integer skillId = (Integer) row[5];
            String skillName = (String) row[6];
            if (skillId != null && skillName != null) {
                ResumeResponse.SkillDTO skill = new ResumeResponse.SkillDTO(
                        skillId, skillName
                );
                resume.addSkill(skill);
            }
        }

        return new ArrayList<>(resumeMap.values());
    }

    // 전체 조회(해당 유저가 작성된 이력서)
    public List<ResumeResponse.DTO> findAllByUserId(Integer idx) {
        String sql = """
                select r.id resume_id, u.id user_id, r.title, u.name, u.image, s.id, s.name, 
                from resume_tb r 
                left outer join skill_tb s on r.id = s.resume_id 
                left outer join user_tb u on u.id = r.user_id 
                where r.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idx);

        List<Object[]> rows = query.getResultList();
        Map<Integer, ResumeResponse.DTO> resumeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            Integer userId = (Integer) row[1];
            String title = (String) row[2];
            String name = (String) row[3];
            String image = (String) row[4];

            ResumeResponse.DTO resume = resumeMap.get(id);
            if (resume == null) {
                resume = new ResumeResponse.DTO(
                        id, userId, title, name, image
                );
                resumeMap.put(id, resume);
            }

            Integer skill_id = (Integer) row[5];
            String skill_name = (String) row[6];
            if (skill_id != null && skill_name != null) {
                ResumeResponse.SkillDTO skill = new ResumeResponse.SkillDTO(
                        skill_id, skill_name
                );
                resume.addSkill(skill);
            }
        }

        return new ArrayList<>(resumeMap.values());
    }

    // 상세 조회(해당 유저가 선택한 작성된 이력서)
    public ResumeResponse.DetailDTO findByResumeId(Integer resumeId) {
        String sql = """
                select r.id resume_id, u.id user_id, r.title, u.name, u.birth, u.tel, u.email, u.address, u.image, r.education, r.major, r.license, r.career, u.role, s.id, s.name 
                from resume_tb r 
                left outer join skill_tb s on r.id = s.resume_id 
                left outer join user_tb u on u.id = r.user_id 
                where r.id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, resumeId);

        List<Object[]> rows = (List<Object[]>) query.getResultList();


        // 한줄 받아서
        Object[] resumeOb = rows.get(0);

        // 파싱하기
        Integer id = (Integer) resumeOb[0];
        Integer userId = (Integer) resumeOb[1];
        String title = (String) resumeOb[2];
        String name = (String) resumeOb[3];
        String birth = (String) resumeOb[4];
        String tel = (String) resumeOb[5];
        String email = (String) resumeOb[6];
        String address = (String) resumeOb[7];
        String image = (String) resumeOb[8];
        String education = (String) resumeOb[9];
        String major = (String) resumeOb[10];
        String license = (String) resumeOb[11];
        String career = (String) resumeOb[12];
        Integer role = (Integer) resumeOb[13];


        ResumeResponse.DetailDTO resume = new ResumeResponse.DetailDTO(
                id, userId, title, name, birth, tel, email, address, image, education, major, license, career, role
        );

        for (Object[] row : rows) {
            Integer skillId = (Integer) row[14];
            String skillName = (String) row[15];

            ResumeResponse.SkillDTO skill = new ResumeResponse.SkillDTO(
                    skillId, skillName
            );

            if (id != null) resume.addSkill(skill);
        }
        return resume;
    }

    // 이력서 삭제
    @Transactional
    public void deleteByResumeId(Integer resumeId) {
        String skillDeleteSql = """
                delete from skill_tb where resume_id =?
                """;
        Query skillDelete = em.createNativeQuery(skillDeleteSql);
        skillDelete.setParameter(1, resumeId);
        skillDelete.executeUpdate();

        String resumeDeleteSql = """
                delete from resume_tb where id = ?
                """;
        Query resumeDelete = em.createNativeQuery(resumeDeleteSql);
        resumeDelete.setParameter(1, resumeId);
        resumeDelete.executeUpdate();
    }

//    // 저장
//    @Transactional
//    public void resumeSave(Integer userId, ResumeRequest.ResumeDTO resume) {
//        String sql = """
//                insert into resume_tb (user_id, title, education, major, license, career, created_at)
//                values (?,?,?,?,?,?,now())
//                """;
//        Query query = em.createNativeQuery(sql);
//        query.setParameter(1, userId);
//        query.setParameter(2, resume.getTitle());
//        query.setParameter(3, resume.getEducation());
//        query.setParameter(4, resume.getMajor());
//        query.setParameter(5, resume.getLicense());
//        query.setParameter(6, resume.getCareer());
//        query.executeUpdate();
//    }
//
//    @Transactional
//    public void skillSave(List<String> skillNames) {
//        for (String skillName : skillNames) {
//            String sql = """
//                    INSERT INTO skill_tb (resume_id, notice_id, name, role)
//                    VALUES (?,?,?,?)
//                    """;
//            Query query = em.createNativeQuery(sql);
//            query.setParameter(1, 1);
//            query.setParameter(2, null);
//            query.setParameter(3, skillName);
//            query.setParameter(4, 1);
//            query.executeUpdate();
//        }
//    }

    // 이력서 저장
    @Transactional
    public void resumeSave(Integer userId, ResumeRequest.ResumeDTO resume, List<String> skillNames) {
        String resumeSql = """
                insert into resume_tb (user_id, title, education, major, license, career, created_at)
                values (?,?,?,?,?,?,now())
                """;
        Query resumeQuery = em.createNativeQuery(resumeSql);
        resumeQuery.setParameter(1, userId);
        resumeQuery.setParameter(2, resume.getTitle());
        resumeQuery.setParameter(3, resume.getEducation());
        resumeQuery.setParameter(4, resume.getMajor());
        resumeQuery.setParameter(5, resume.getLicense());
        resumeQuery.setParameter(6, resume.getCareer());
        resumeQuery.executeUpdate();

        String findResumeIdSql = "SELECT LAST_INSERT_ID()";
        Query findResumeIdQuery = em.createNativeQuery(findResumeIdSql);
        Long resumeId = (Long) findResumeIdQuery.getSingleResult();

        for (String skillName : skillNames) {
            String skillSql = """
                    INSERT INTO skill_tb (resume_id, notice_id, name, role)
                    VALUES (?,?,?,?)
                    """;
            Query skillQuery = em.createNativeQuery(skillSql);
            skillQuery.setParameter(1, resumeId);
            skillQuery.setParameter(2, null);
            skillQuery.setParameter(3, skillName);
            skillQuery.setParameter(4, 1);
            skillQuery.executeUpdate();
        }
    }

    // 이력서 수정
    @Transactional
    public void resumeUpdate(Integer resumeId, ResumeRequest.ResumeDTO resume, List<String> skillNames) {
        String skillDeleteSql = """
                delete from skill_tb where resume_id =?
                """;
        Query skillDelete = em.createNativeQuery(skillDeleteSql);
        skillDelete.setParameter(1, resumeId);
        skillDelete.executeUpdate();

        String resumeSql = """
                update resume_tb set title = ?, education = ?, major = ?, license = ?, career = ? 
                where id = ?
                """;
        Query resumeQuery = em.createNativeQuery(resumeSql);
        resumeQuery.setParameter(1, resume.getTitle());
        resumeQuery.setParameter(2, resume.getEducation());
        resumeQuery.setParameter(3, resume.getMajor());
        resumeQuery.setParameter(4, resume.getLicense());
        resumeQuery.setParameter(5, resume.getCareer());
        resumeQuery.setParameter(6, resumeId);
        resumeQuery.executeUpdate();

        for (String skillName : skillNames) {
            String skillSql = """
                    INSERT INTO skill_tb (resume_id, notice_id, name, role)
                    VALUES (?,?,?,?)
                    """;
            Query skillQuery = em.createNativeQuery(skillSql);
            skillQuery.setParameter(1, resumeId);
            skillQuery.setParameter(2, null);
            skillQuery.setParameter(3, skillName);
            skillQuery.setParameter(4, 1);
            skillQuery.executeUpdate();
        }
    }
}

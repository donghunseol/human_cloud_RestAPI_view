package com.example.project1.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {
    private final EntityManager em;

    // 전체 조회(모든 유저가 작성된 이력서)
    public List<ResumeResponse.ResumeListDTO> findAll() {
        String sql = """
                select r.id resume_id, u.id user_id, r.title, u.name, u.image, s.id, s.name, 
                from resume_tb r 
                left outer join skill_tb s on r.id = s.resume_id 
                left outer join user_tb u on u.id = r.user_id
                """;
        Query query = em.createNativeQuery(sql);

        List<Object[]> rows = query.getResultList();
        Map<Integer, ResumeResponse.ResumeListDTO> resumeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            Integer user_id = (Integer) row[1];
            String title = (String) row[2];
            String name = (String) row[3];
            String image = (String) row[4];

            ResumeResponse.ResumeListDTO resume = resumeMap.get(id);
            if (resume == null) {
                resume = new ResumeResponse.ResumeListDTO(
                        id, user_id, title, name, image
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

    // 전체 조회(해당 유저가 작성된 이력서)
    public List<ResumeResponse.ResumeListDTO> findAllByUserId(Integer idx) {
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
        Map<Integer, ResumeResponse.ResumeListDTO> resumeMap = new HashMap<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            Integer user_id = (Integer) row[1];
            String title = (String) row[2];
            String name = (String) row[3];
            String image = (String) row[4];

            ResumeResponse.ResumeListDTO resume = resumeMap.get(id);
            if (resume == null) {
                resume = new ResumeResponse.ResumeListDTO(
                        id, user_id, title, name, image
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
    public ResumeResponse.ResumeDetailDTO findByResumeId(Integer resumeId) {
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

        Integer id = (Integer) rows.get(0)[0];
        Integer user_id = (Integer) rows.get(0)[1];
        String title = (String) rows.get(0)[2];
        String name = (String) rows.get(0)[3];
        String birth = (String) rows.get(0)[4];
        String tel = (String) rows.get(0)[5];
        String email = (String) rows.get(0)[6];
        String address = (String) rows.get(0)[7];
        String image = (String) rows.get(0)[8];
        String education = (String) rows.get(0)[9];
        String major = (String) rows.get(0)[10];
        String license = (String) rows.get(0)[11];
        String career = (String) rows.get(0)[12];
        Integer role = (Integer) rows.get(0)[13];


        ResumeResponse.ResumeDetailDTO resume = new ResumeResponse.ResumeDetailDTO(
                id, user_id, title, name, birth, tel, email, address, image, education, major, license, career, role
        );

        for (Object[] row : rows) {
            Integer skill_id = (Integer) row[14];
            String skill_name = (String) row[15];

            ResumeResponse.SkillDTO skill = new ResumeResponse.SkillDTO(
                    skill_id, skill_name
            );

            if (id!= null) resume.addSkill(skill);
        }
        return resume;
    }

    @Transactional
    public void deleteByResumeId(Integer resumeId) {
        String skill_delete_sql = """
                delete from skill_tb where resume_id =?
                """;
        Query skill_delete = em.createNativeQuery(skill_delete_sql);
        skill_delete.setParameter(1, resumeId);

        String resume_delete_sql = """
                delete from resume_tb where id = ?
                """;
        Query resume_delete = em.createNativeQuery(resume_delete_sql);
        resume_delete.setParameter(1, resumeId);
        resume_delete.executeUpdate();
    }

    // 저장
    @Transactional
    public void save() {

    }

    // 수정
    @Transactional
    public void update() {

    }
}

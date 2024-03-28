package com.example.project_v2.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class SkillQueryRepository {
    private final EntityManager em;

    @Transactional
    public void deleteByResumeId(Integer resumeId){
        String sql = """
                delete from skill_tb where resume_id = ?;
                """;

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, resumeId);

        query.executeUpdate();
    }
}

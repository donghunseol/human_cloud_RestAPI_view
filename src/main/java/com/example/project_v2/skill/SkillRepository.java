package com.example.project_v2.skill;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class SkillRepository {
    private final EntityManager em;

    // 부분 조회
    public void findById(){

    }

    // 전체 조회
    public void findAll(){

    }

    // 저장
    @Transactional
    public void skillSave(Integer resumeId,  SkillRequest.skillDTO skill){
        String sql = """
                insert into skill_tb (resume_id, name)
                values (?,?)
                """;

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, resumeId);
        query.setParameter(2, skill.getName());

        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(){

    }

    // 수정
    @Transactional
    public void update(){

    }
}

package com.example.project_v2.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillJPARepository extends JpaRepository<Skill, Integer> {

    @Modifying
    @Query("delete from Skill s where s.notice.id = :id")
    void deleteAllByNoticeId(@Param("id") Integer id);
}

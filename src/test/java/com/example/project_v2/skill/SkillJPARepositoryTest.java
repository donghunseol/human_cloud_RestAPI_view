package com.example.project_v2.skill;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class SkillJPARepositoryTest {

    @Autowired
    private SkillJPARepository skillJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void deleteAllByNoticeId_test(){
        // given
        int id = 2;

        // when
        skillJPARepository.deleteAllByNoticeId(id);
        em.flush();

        // then

    }
}

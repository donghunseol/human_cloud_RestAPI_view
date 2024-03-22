package com.example.project_v2.skill;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SkillJPARepositoryTest {

    @Autowired
    private SkillJPARepository skillJPARepository;

    @Autowired
    private EntityManager em;

}

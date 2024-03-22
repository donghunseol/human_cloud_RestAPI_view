package com.example.project_v2.apply;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ApplyJPARepositoryTest {

    @Autowired
    private ApplyJPARepository applyJPARepository;

    @Autowired
    private EntityManager em;

}

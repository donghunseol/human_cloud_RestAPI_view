package com.example.project_v2.love;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LoveJPARepositoryTest {

    @Autowired
    private LoveJPARepository loveJPARepository;

    @Autowired
    private EntityManager em;

}

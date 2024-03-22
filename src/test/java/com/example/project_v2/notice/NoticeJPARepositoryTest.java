package com.example.project_v2.notice;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class NoticeJPARepositoryTest {

    @Autowired
    private NoticeJPARepository noticeJPARepository;

    @Autowired
    private EntityManager em;

}

package com.example.project_v2.notice;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class NoticeJPARepositoryTest {

    @Autowired
    private NoticeJPARepository noticeJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByIdJoinUser_test(){
        //given
        int id =7;
        //when
        Optional<Notice> noticeOP = noticeJPARepository.findByIdJoinUser(id);
        //then
        assertTrue(noticeOP.isPresent());
        assertEquals(id, noticeOP.get().getId());
        assertNotNull(noticeOP.get().getUser()); // User 정보도 함께 로드되었는지 확인
    }

}

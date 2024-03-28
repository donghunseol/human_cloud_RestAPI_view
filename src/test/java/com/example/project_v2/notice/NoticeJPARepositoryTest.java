package com.example.project_v2.notice;

import com.example.project_v2.user.UserJPARepository;
import jakarta.persistence.EntityManager;
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
    public void delete_test(){
        // given
        Integer id = 1;

        // when
        noticeJPARepository.deleteById(id); // LAZY 로 skill 과 같이 지워지는지 테스트
        em.flush();

        // then

    }

    @Test
    public void findByIdJoinSkill_test(){
        // given
        int id = 2;

        // when
        Optional<Notice> noticeOP = noticeJPARepository.findByIdJoinSkill(id);
        System.out.println("save_test/skills/size : " + noticeOP.get().getSkills().size());
        System.out.println("save_test/skills/first/name : " + noticeOP.get().getSkills().getFirst().getName());
        System.out.println("save_test/skills/last/name : " + noticeOP.get().getSkills().getLast().getName());

        // then
        assertTrue(noticeOP.isPresent());
        assertEquals(id, noticeOP.get().getId());
        assertNotNull(noticeOP.get().getUser());
    }

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

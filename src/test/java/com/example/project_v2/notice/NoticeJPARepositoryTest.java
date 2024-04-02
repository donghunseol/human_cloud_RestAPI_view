package com.example.project_v2.notice;

import com.example.project_v2.board.Board;
import com.example.project_v2.user.UserJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    public void findByIdJoinUser_test(){
        //given
        int id = 5;
        //when
        Optional<Notice> noticeOP = noticeJPARepository.findByIdJoinUser(id);
        //then
        assertTrue(noticeOP.isPresent());
        assertEquals(id, noticeOP.get().getId());
        assertNotNull(noticeOP.get().getUser()); // User 정보도 함께 로드되었는지 확인
    }

    @Test
    public void findAll_test() {
        //given

        //when
        List<Notice> noticeList = noticeJPARepository.findAll();

        //then
        System.out.println("findAll_test/size : " + noticeList.size()); // 공고글 목록 수
        System.out.println("findAll_test/username : " + noticeList.get(2).getUser().getUsername()); //공고 쓴 유저 확인

        assertThat(noticeList.size()).isEqualTo(5);
        assertThat(noticeList.get(2).getUser().getUsername()).isEqualTo("comlove");
    }

}

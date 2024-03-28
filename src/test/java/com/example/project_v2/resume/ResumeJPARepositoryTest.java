package com.example.project_v2.resume;

import com.example.project_v2.board.Board;
import com.example.project_v2.user.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ResumeJPARepositoryTest {

    @Autowired
    private ResumeJPARepository resumeJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Resume resume = Resume.builder()
                .user(sessionUser)
                .title("제목5")
                .career("1년")
                .license("정보처리기사, SQLD")
                .education("쌀대학교, 졸업")
                .major("컴퓨터공학과")
                .build();

        // when
        resumeJPARepository.save(resume);

        // then
        Assertions.assertThat(resume.getId()).isEqualTo(7);
    }
    @Test
    public void findByIdJoinUser_test() {
        // Given
        int id = 2;

        // When
        Optional<Resume> optionalResume = resumeJPARepository.findByIdJoinUser(id);

        // Then
        assertTrue(optionalResume.isPresent()); // 해당 id에 해당하는 Resume가 존재하는지 확인
        assertNotNull(optionalResume.get().getUser()); // Resume에 연결된 User가 존재하는지 확인
    }
}

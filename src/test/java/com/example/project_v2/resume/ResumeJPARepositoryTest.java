package com.example.project_v2.resume;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
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
    public void findByIdJoinUser_test() {
        // Given
        int id = 2;

        // When
        Optional<Resume> optionalResume = resumeJPARepository.findByIdJoinUser(id);

        // Then
        assertTrue(optionalResume.isPresent()); // 해당 id에 해당하는 Resume가 존재하는지 확인
        assertNotNull(optionalResume.get().getUser()); // Resume에 연결된 User가 존재하는지 확인
    }

    @Test
    public void delete_test(){
        // given
        int id = 1;

        // when
        resumeJPARepository.deleteById(1);
        em.flush();

        List<Resume> resumeList = resumeJPARepository.findAll();

        // then
        Assertions.assertThat(resumeList.size()).isEqualTo(5);

    }

    @Test
    public void findAll_test(){
        // given


        // when
        List<Resume> resumeList = resumeJPARepository.findAll();

        // then
        Assertions.assertThat(resumeList.size()).isEqualTo(6);

    }
}

package com.example.project_v2.resume;

import com.example.project_v2.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    public void findByUser_test() {
        // Given
        User user = new User();
        user.setId(1);

        // When
        List<Resume> result = resumeJPARepository.findByUser(user, Sort.unsorted());

        // Then
        assertEquals(4, result.size()); // 해당 사용자에 대한 이력서 개수를 확인
    }

    @Test
    public void findAll_test() {
        // When
        List<Resume> result = resumeJPARepository.findAll();

        // Then
        assertEquals(6, result.size()); // 전체 이력서 개수를 확인
    }
}

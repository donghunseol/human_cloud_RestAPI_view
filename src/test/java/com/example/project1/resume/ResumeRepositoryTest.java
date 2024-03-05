package com.example.project1.resume;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(ResumeRepository.class)
@DataJpaTest
public class ResumeRepositoryTest {

    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void testFindAll() {
        List<ResumeResponse.DTO> resumeList = resumeRepository.findAll();

        resumeList.forEach(System.out::println);
    }

    @Test
    public void testFindAllByUserId() {
        Integer userId = 1;

        List<ResumeResponse.DTO> resumeList = resumeRepository.findAllByUserId(userId);

        resumeList.forEach(System.out::println);
    }

    @Test
    public void testFindByResumeId() {
        Integer resumeId = 1;

        System.out.println(resumeRepository.findByResumeId(resumeId));
    }

    @Test
    public void testDeleteByResumeId() {
        Integer resumeId = 1;

        resumeRepository.deleteByResumeId(resumeId);

        List<ResumeResponse.DTO> resumeList = resumeRepository.findAll();

        resumeList.forEach(System.out::println);

    }
}

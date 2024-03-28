package com.example.project_v2.skill;

import org.assertj.core.api.Assertions;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class SkillJPARepositoryTest {

    @Autowired
    private SkillJPARepository skillJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void saveAll_test(){
        // given
        Resume resume = Resume.builder().id(1).build();

        List<Skill> skillList = new ArrayList<>();
        Skill skill1 = Skill.builder()
                .resume(resume)
                .name("JAVA")
                .role(0)
                .build();
        skillList.add(skill1);

        Skill skill2 = Skill.builder()
                .resume(resume)
                .name("C++")
                .role(0)
                .build();
        skillList.add(skill2);

        // when
        skillList = skillJPARepository.saveAll(skillList);
        resume.setSkills(skillList);

        // then
        Assertions.assertThat(resume.getSkills().size()).isEqualTo(2);
        Assertions.assertThat(resume.getSkills().get(0).getName()).isEqualTo("JAVA");
        Assertions.assertThat(resume.getSkills().get(1).getName()).isEqualTo("C++");

    }

}

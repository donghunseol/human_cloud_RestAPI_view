package com.example.project_v2.resume;

import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.project_v2.skill.Skill;
import com.example.project_v2.skill.SkillJPARepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Resume save(ResumeRequest.SaveDTO reqDTO, User sessionUser){
        // 이력서 정보 저장
        Resume resume = reqDTO.toEntity(sessionUser);
        resume = resumeJPARepository.save(resume);

        // 스킬 정보를 생성
        List<Skill> skills = new ArrayList<>();
        for (ResumeRequest.SaveDTO.SkillDTO skillDTO : reqDTO.getSkills()) {
            Skill skill = Skill.builder()
                    .resume(resume)
                    .name(skillDTO.getName())
                    .role(skillDTO.getRole())
                    .build();
            skills.add(skill);
        }

        // 이력서에 스킬 정보 추가
        skills = skillJPARepository.saveAll(skills);
        resume.setSkills(skills);

        return resumeJPARepository.save(resume);

    }

    public ResumeResponse.DetailDTO resumeDetail(int resumeId, User sessionUser) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
        return new ResumeResponse.DetailDTO(resume, sessionUser);
    }
}

package com.example.project_v2.resume;

import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.apply.ApplyJPARepository;
import com.example.project_v2.scrap.ScrapJPARepository;
import com.example.project_v2.skill.Skill;
import com.example.project_v2.skill.SkillJPARepository;
import com.example.project_v2.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final SkillJPARepository skillJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final ScrapJPARepository scrapJPARepository;

    @Transactional
    public ResumeResponse.DTO update(Integer resumeId, User sessionUser, ResumeRequest.UpdateDTO reqDTO, List<String> skillNames) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));

        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("이력서를 수정할 권한이 없습니다.");
        }

        resume.setUser(reqDTO.getUser());
        resume.setTitle(reqDTO.getTitle());
        resume.setCareer(reqDTO.getCareer());
        resume.setLicense(reqDTO.getLicense());
        resume.setEducation(reqDTO.getEducation());
        resume.setMajor(reqDTO.getMajor());
        resume.setUser(sessionUser); // 세션 사용자를 공고의 사용자로 설정

        // 여기까지 이력서 정보 업데이트
        resumeJPARepository.save(resume); // 변경된 이력서 정보를 저장

        // 이력서에 스킬 정보 삭제 후, 추가
        if (!skillJPARepository.findByResumeId(resumeId).isEmpty()) {
            skillJPARepository.deleteAllByResumeId(resumeId);
        }

        // 스킬 정보를 생성
        List<Skill> skills = new ArrayList<>();
        for (String skillName : skillNames) {
            Skill skill = new Skill();
            skill.setName(skillName);
            skill.setRole(sessionUser.getRole());
            skill.setResume(resume);
            skills.add(skill);
        }

        // 스킬 정보를 저장하고 저장된 스킬 목록을 반환받음
        List<Skill> skillList = skillJPARepository.saveAll(skills);

        // 이력서에 저장된 스킬 목록을 설정
        resume.setSkills(skillList);

        Resume newResume = resumeJPARepository.save(resume);

        return new ResumeResponse.DTO(newResume, sessionUser);
    }

    @Transactional
    public void delete(Integer resumeId, Integer sessionUserId) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("이력서를 삭제할 권한이 없습니다.");
        }
        // 공고에 연관된 모든 지원 정보 삭제
        applyJPARepository.deleteByResumeId(resumeId);
        scrapJPARepository.deleteByResumeId(resumeId);

        resumeJPARepository.deleteById(resumeId);
    }

    @Transactional
    public ResumeResponse.DTO save(ResumeRequest.SaveDTO reqDTO, User sessionUser, List<String> skillNames) {
        // 이력서 정보 저장
        Resume resume = resumeJPARepository.save(reqDTO.toEntity(sessionUser));

        // 스킬 정보를 생성
        List<Skill> skills = new ArrayList<>();
        for (String skillName : skillNames) {
            Skill skill = new Skill();
            skill.setName(skillName);
            skill.setRole(sessionUser.getRole());
            skill.setResume(resume);
            skills.add(skill);
        }

        // 이력서에 스킬 정보 추가
        List<Skill> skillList = skillJPARepository.saveAll(skills);
        resume.setSkills(skillList);

        Resume newResume = resumeJPARepository.save(resume);

        return new ResumeResponse.DTO(newResume, sessionUser);
    }

    // 이력서 상세보기
    public ResumeResponse.DetailDTO resumeDetail(int resumeId, User sessionUser) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
        return new ResumeResponse.DetailDTO(resume, sessionUser);
    }

    // 이력서 리스트
    public List<ResumeResponse.ResumeListDTO> resumeList(Pageable pageable) {
        Page<Resume> resumeList = resumeJPARepository.findAll(pageable);
        return resumeList.stream().map(resume -> new ResumeResponse.ResumeListDTO(resume)).toList();
    }

    // 이력서 리스트(개인)
    public List<ResumeResponse.ResumeListDTO> resumeListByUser(User user, Pageable pageable) {
        List<Resume> resumeList = resumeJPARepository.findByUser(user, pageable);
        return resumeList.stream().map(resume -> new ResumeResponse.ResumeListDTO(resume)).toList();
    }
}

package com.example.project_v2.notice;

import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.apply.ApplyJPARepository;
import com.example.project_v2.scrap.ScrapJPARepository;
import com.example.project_v2.skill.Skill;
import com.example.project_v2.skill.SkillJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeJPARepository noticeJPARepository;
    private final SkillJPARepository skillJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final ScrapJPARepository scrapJPARepository;

    @Transactional
    public NoticeResponse.DTO update(Integer noticeId, NoticeRequest.UpdateDTO reqDTO, User sessionUser, List<String> skillNames) throws Exception404 {
        Notice notice = noticeJPARepository.findById(noticeId)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다."));

        if (sessionUser.getId() != notice.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다.");
        }

        notice.setTitle(reqDTO.getTitle());
        notice.setType(reqDTO.getType());
        notice.setField(reqDTO.getField());
        notice.setWorkPlace(reqDTO.getWorkPlace());
        notice.setDeadline(reqDTO.getDeadline());
        notice.setContent(reqDTO.getContent());
        notice.setUser(sessionUser); // 세션 사용자를 공고의 사용자로 설정

        // 여기까지 공고 정보 업데이트
        noticeJPARepository.save(notice); // 변경된 공고 정보를 저장

        // 공고 스킬 정보 삭제 후, 추가 (스킬이 없는 경우에는 삭제 안함)
        if (!skillJPARepository.findByNoticeId(noticeId).isEmpty()) {
            skillJPARepository.deleteAllByNoticeId(noticeId);
        }

        // 스킬 정보 생성
        List<Skill> skills = new ArrayList<>();
        for (String skillName : skillNames) {
            // 새로운 Skill 객체 생성
            Skill skill = new Skill();
            skill.setName(skillName);
            skill.setNotice(notice);
            skills.add(skill);
        }

        // 스킬 정보를 저장하고 저장된 스킬 목록을 반환받음
        List<Skill> savedSkills = skillJPARepository.saveAll(skills);

        // 공고에 저장된 스킬 목록을 설정
        notice.setSkills(savedSkills);

        return new NoticeResponse.DTO(notice, sessionUser);
    }




    public NoticeResponse.DetailDTO noticeDetail(Integer noticeId, User sessionUser) {
        Notice notice = noticeJPARepository.findByIdJoinUser(noticeId)
                .orElseThrow(() -> new Exception404("공고 글을 찾을 수 없음"));
        return new NoticeResponse.DetailDTO(notice, sessionUser);
    }

    @Transactional
    public void delete(Integer noticeId, Integer sessionUserId) {
        Notice notice = noticeJPARepository.findById(noticeId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));

        if (sessionUserId != notice.getUser().getId()) {
            throw new Exception403("공고를 삭제할 권한이 없습니다");
        }
        // 공고에 연관된 모든 지원 정보 삭제
        applyJPARepository.deleteByNoticeId(noticeId);
        scrapJPARepository.deleteByNoticeId(noticeId);

        // 공고 삭제
        noticeJPARepository.deleteById(noticeId);
    }

    @Transactional
    public NoticeResponse.DTO save(NoticeRequest.SaveDTO reqDTO, User sessionUser, List<String> skillNames) {
        Notice notice = noticeJPARepository.save(reqDTO.toEntity(sessionUser));

        // 1번 방법 -> skill 로 안받으면 reqDTO 의 id 값이 null 로 json이 뜬다
//        reqDTO.getSkills().forEach(skill -> {
//            skill.setNotice(notice);
//            skillJPARepository.save(skill.toEntity());
//        });

        // 2번 방법
        // 스킬 정보 생성
        List<Skill> skills = new ArrayList<>();
        for (String skillName : skillNames) {
            // 새로운 Skill 객체 생성
            Skill skill = new Skill();
            skill.setName(skillName);
            skill.setRole(sessionUser.getRole());
            skill.setNotice(notice);
            skills.add(skill);
        }

        // 스킬 정보를 저장하고 저장된 스킬 목록을 반환받음
        List<Skill> savedSkills = skillJPARepository.saveAll(skills);

        // 공고에 저장된 스킬 목록을 설정
        notice.setSkills(savedSkills);

        Notice newNotice = noticeJPARepository.save(notice);

        return new NoticeResponse.DTO(newNotice, sessionUser);
    }

    public List<NoticeResponse.NoticeListDTO> noticeList(Pageable pageable) {
        Page<Notice> noticeList = noticeJPARepository.findAll(pageable);
        return noticeList.stream().map(notice -> new NoticeResponse.NoticeListDTO(notice)).toList();
    }

    public List<NoticeResponse.NoticeListDTO> noticeListByUser(User sessionUser, Pageable pageable) {
        List<Notice> noticeList = noticeJPARepository.findByUser(sessionUser, pageable);
        return noticeList.stream().map(notice -> new NoticeResponse.NoticeListDTO(notice)).toList();
    }
}

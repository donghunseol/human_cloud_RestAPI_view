package com.example.project_v2.notice;

import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.board.Board;
import com.example.project_v2.board.BoardResponse;
import com.example.project_v2.skill.Skill;
import com.example.project_v2.skill.SkillJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeJPARepository noticeJPARepository;
    private final SkillJPARepository skillJPARepository;

    public NoticeResponse.DetailDTO noticeDetail(Integer noticeId, User sessionUser) {
        Notice notice = noticeJPARepository.findByIdJoinUser(noticeId)
                .orElseThrow(() -> new Exception404("공고 글을 찾을 수 없음"));

        return new NoticeResponse.DetailDTO(notice, sessionUser);
    }

    public void delete(Integer noticeId, Integer sessionUserId){
        Notice notice = noticeJPARepository.findById(noticeId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));

        if(sessionUserId != notice.getUser().getId()){
            throw new Exception403("공고를 삭제할 권한이 없습니다");
        }
        noticeJPARepository.deleteById(noticeId);
    }

    @Transactional
    public Notice save(NoticeRequest.SaveDTO reqDTO, User sessionUser) {
        Notice notice = noticeJPARepository.save(reqDTO.toEntity(sessionUser));

        // 1번 방법 -> skill 로 안받으면 reqDTO 의 id 값이 null 로 json이 뜬다
//        reqDTO.getSkills().forEach(skill -> {
//            skill.setNotice(notice);
//            skillJPARepository.save(skill.toEntity());
//        });

        // 2번 방법
        List<Skill> skills = new ArrayList<>();
        for (NoticeRequest.SaveDTO.SkillDTO skill : reqDTO.getSkills()) {
            Skill skillBuild = Skill.builder()
                    .name(skill.getName())
                    .role(skill.getRole())
                    .notice(notice)
                    .build();
            skills.add(skillBuild);
        }
        skills = skillJPARepository.saveAll(skills);
        notice.setSkills(skills);

        return noticeJPARepository.save(notice);
    }

    public List<NoticeResponse.MainDTO> noticeMain() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Notice> noticeList = noticeJPARepository.findAll(sort);
        return noticeList.stream().map(notice -> new NoticeResponse.MainDTO(notice)).toList();
    }
}

package com.example.project_v2.apply;

import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.board.BoardResponse;
import com.example.project_v2.notice.Notice;
import com.example.project_v2.notice.NoticeJPARepository;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final NoticeJPARepository noticeJPARepository;
    private final ResumeJPARepository resumeJPARepository;


    // 이력서 조회
    public ApplyResponse.SelectResumeDTO findById(Integer applyId, User sessionUser){
       Apply apply = applyJPARepository.findById(applyId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return new ApplyResponse.SelectResumeDTO(apply, sessionUser);
    }

    @Transactional
    public Apply save(ApplyRequest.SaveDTO reqDTO, User sessionUser) {
        Optional<Notice> optionalNotice = reqDTO.getNoticeId() == null ? Optional.empty() : noticeJPARepository.findById(reqDTO.getNoticeId());
        if (!optionalNotice.isPresent()) {
            // 공고 ID가 null이 아니지만 찾을 수 없는 경우의 처리 로직
            throw new Exception404("공고를 찾을 수 없습니다");
        }

        Optional<Resume> optionalResume = reqDTO.getResumeId() == null ? Optional.empty() : resumeJPARepository.findById(reqDTO.getResumeId());
        if (!optionalResume.isPresent()) {
            // 이력서 ID가 null이 아니지만 찾을 수 없는 경우의 처리 로직
            throw new Exception404("이력서를 찾을 수 없습니다");
        }

        // Apply 엔티티 생성 시, Notice와 Resume이 null일 수 있으므로 Optional의 orElse(null)을 사용하여 처리
        Apply apply = applyJPARepository.save(reqDTO.toEntity(sessionUser, optionalNotice.orElse(null), optionalResume.orElse(null)));
        return apply;
    }


}

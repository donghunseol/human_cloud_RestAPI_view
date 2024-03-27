package com.example.project_v2.resume;

import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;

    public ResumeResponse.DetailDTO resumeDetail(int resumeId, User sessionUser) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
        return new ResumeResponse.DetailDTO(resume, sessionUser);
    }
}

package com.example.project_v2.resume;

import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;

    // 이력서 상세보기
    public ResumeResponse.DetailDTO resumeDetail(int resumeId, User sessionUser) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
        return new ResumeResponse.DetailDTO(resume, sessionUser);
    }

    // 이력서 리스트
    public List<ResumeResponse.ResumeListDTO> resumeList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Resume> resumeList = resumeJPARepository.findAll(sort);
        return resumeList.stream().map(resume -> new ResumeResponse.ResumeListDTO(resume)).toList();
    }

    // 이력서 리스트(개인)
    public List<ResumeResponse.ResumeListDTO> resumeListByUser(User user) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Resume> resumeList = resumeJPARepository.findByUser(user, sort);
        return resumeList.stream().map(resume -> new ResumeResponse.ResumeListDTO(resume)).toList();
    }
}

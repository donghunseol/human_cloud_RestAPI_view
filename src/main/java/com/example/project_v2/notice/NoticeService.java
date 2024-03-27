package com.example.project_v2.notice;

import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeJPARepository noticeJPARepository;

    public NoticeResponse.DetailDTO noticeDetail(Integer noticeId, User sessionUser){

        Notice notice = noticeJPARepository.findByIdJoinUser(noticeId)
                .orElseThrow(() -> new Exception404("공고 글을 찾을 수 없음"));

        return new NoticeResponse.DetailDTO(notice, sessionUser);
    }
}

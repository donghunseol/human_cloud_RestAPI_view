package com.example.project_v2.scrap;

import com.example.project_v2.notice.NoticeJPARepository;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    public Scrap save(Integer id, ScrapRequest.SaveDTO reqDTO, User sessionUser) {
        Scrap scrap = scrapJPARepository.save(reqDTO.toEntity(sessionUser, id));
        return scrap;
    }
}

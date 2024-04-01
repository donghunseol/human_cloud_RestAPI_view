package com.example.project_v2.scrap;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.notice.NoticeJPARepository;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    public Scrap save(Resume resume, ScrapRequest.SaveDTO reqDTO, User sessionUser) {
        Scrap scrap = scrapJPARepository.save(reqDTO.toEntity(sessionUser, resume));
        return scrap;
    }

    public Scrap save(Notice notice, ScrapRequest.SaveDTO reqDTO, User sessionUser) {
        Scrap scrap = scrapJPARepository.save(reqDTO.toEntity(sessionUser, notice));
        return scrap;
    }
}

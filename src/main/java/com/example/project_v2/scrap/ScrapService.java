package com.example.project_v2.scrap;

import com.example.project_v2.notice.Notice;
import com.example.project_v2.notice.NoticeJPARepository;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;

    @Transactional
    public Scrap save(Resume resume, ScrapRequest.SaveDTO reqDTO, User sessionUser) {
        Scrap scrap = scrapJPARepository.save(reqDTO.toEntity(sessionUser, resume));
        return scrap;
    }

    @Transactional
    public Scrap save(Notice notice, ScrapRequest.SaveDTO reqDTO, User sessionUser) {
        Scrap scrap = scrapJPARepository.save(reqDTO.toEntity(sessionUser, notice));
        return scrap;
    }
}

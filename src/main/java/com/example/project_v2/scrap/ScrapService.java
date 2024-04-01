package com.example.project_v2.scrap;

import com.example.project_v2._core.errors.exception.Exception401;
import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2.notice.Notice;
import com.example.project_v2.notice.NoticeJPARepository;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Transactional
    public void delete(Integer id, User sessionUser) {
        Optional<Scrap> scrapOP = null;
        Scrap scrap = null;

        if(sessionUser.getRole()==0){
            scrapOP = scrapJPARepository.findByNoticeIdAndUserId(id, sessionUser.getId());
        }else{
            scrapOP = scrapJPARepository.findByResumeIdAndUserId(id, sessionUser.getId());
        }

        if(scrapOP.isEmpty()){
            throw new Exception401("존재하지 않는 스크랩입니다.");
        }else{
            scrap = scrapOP.get();
        }

        if(scrap.getUser().getId() != sessionUser.getId()){
            throw new Exception403("스크랩을 삭제할 권한이 없습니다.");
        }

        scrapJPARepository.deleteById(scrap.getId());
    }
}

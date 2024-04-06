package com.example.project_v2.user;

import com.example.project_v2._core.errors.exception.Exception400;
import com.example.project_v2._core.errors.exception.Exception401;
import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2._core.util.ImageSave;
import com.example.project_v2.apply.Apply;
import com.example.project_v2.apply.ApplyJPARepository;
import com.example.project_v2.notice.Notice;
import com.example.project_v2.notice.NoticeJPARepository;
import com.example.project_v2.notice.NoticeResponse;
import com.example.project_v2.resume.Resume;
import com.example.project_v2.resume.ResumeJPARepository;
import com.example.project_v2.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final NoticeJPARepository noticeJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final ImageSave imageSave;

    public User sameCheck(String username) {
        User user = userJPARepository.findByUsername(username)
                .orElseThrow(() -> new Exception401("사용할 수 있는 아이디입니다"));
        return user;
    }

    @Transactional
    public UserResponse.DTO join(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());

        if(userOP.isPresent()){
            throw new Exception400("중복된 유저네임입니다");
        }

        User user = userJPARepository.save(reqDTO.toEntity());

        return new UserResponse.DTO(user);
    }

    public User login(UserRequest.LoginDTO reqDTO) {
        User user = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return user;
    }

    //TODO: 프로필 완료
    @Transactional
    public User update(Integer id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다."));

        // 사진이 변경되었는지 여부 확인 후 세이브
        String webImgPath = null;
        if (imageSave.ImageCheck(reqDTO.getEncodedData())) {
            webImgPath = imageSave.saveImageFile(reqDTO.getEncodedData(), reqDTO.getImageName());
        }

        user.setUpdateDTO(reqDTO, webImgPath != null ? webImgPath : user.getEncodedData());
        return user;
    }

    // 사용자의 role 에 따라 메인페이지 화면 변경
    public List<?> getMainPageByUserRole(User sessionUser, Pageable pageable) {
        List<?> resultList = new ArrayList<>();

        if (sessionUser != null) { // 로그인시
            if (sessionUser.getRole() == 1) {
                // Role이 1인 경우 Resume 리스트 반환
                resultList = resumeJPARepository.findAll(pageable).stream()
                        .map(resume -> new ResumeResponse.ResumeListDTO((Resume) resume))
                        .toList();
            } else {
                // Role이 0인 경우 Notice 리스트 반환
                resultList = noticeJPARepository.findAll(pageable).stream()
                        .map(notice -> new NoticeResponse.NoticeListDTO((Notice) notice))
                        .toList();
            }
        } else { // 로그인하지 않은 경우 Notice 리스트 반환
            resultList = noticeJPARepository.findAll(pageable).stream()
                    .map(notice -> new NoticeResponse.NoticeListDTO((Notice) notice))
                    .toList();
        }
        return resultList;
    }

    // 메인페이지 스킬 검색 서비스
    public List<?> getMainPageByUserRoleAndSkill(User sessionUser, String skillName, Pageable pageable) {
        List<?> resultList = new ArrayList<>();

        if (sessionUser != null) { // 로그인시
            if (sessionUser.getRole() == 1) {
                // Role이 1인 경우 Resume 리스트 반환
                resultList = resumeJPARepository.findBySkill(skillName, pageable).stream()
                        .map(resume -> new ResumeResponse.ResumeListDTO((Resume) resume))
                        .toList();
            } else {
                // Role이 0인 경우 Notice 리스트 반환
                resultList = noticeJPARepository.findBySkill(skillName, pageable).stream()
                        .map(notice -> new NoticeResponse.NoticeListDTO((Notice) notice))
                        .toList();
            }
        } else { // 로그인하지 않은 경우 Notice 리스트 반환
            resultList = noticeJPARepository.findBySkill(skillName, pageable).stream()
                    .map(notice -> new NoticeResponse.NoticeListDTO((Notice) notice))
                    .toList();
        }
        return resultList;
    }

    // 마이페이지
    public List<?> getMyPage(User sessionUser, Pageable pageable) {
        List<?> myPageList = new ArrayList<>();

        if (sessionUser != null) { // 로그인시
            if (sessionUser.getRole() == 1) { // 기업
                // Role이 1인 경우 Resume 리스트 반환
                myPageList = noticeJPARepository.findByUser(sessionUser, pageable).stream()
                        .map(notice -> new NoticeResponse.NoticeListDTO((Notice) notice))
                        .toList();
            } else {
                // Role이 0인 경우 Notice 리스트 반환
                myPageList = resumeJPARepository.findByUser(sessionUser, pageable).stream()
                        .map(resume -> new ResumeResponse.ResumeListDTO((Resume) resume))
                        .toList();
            }
        } else { // 로그인을 하지 않을 경우
            throw new Exception401("회원정보를 찾을 수 없습니다.");
        }
        return myPageList;
    }

    public List<Apply> findAppliesByUserId(Integer userId) {
        return applyJPARepository.findAppliesByUserId(userId);
    }
}

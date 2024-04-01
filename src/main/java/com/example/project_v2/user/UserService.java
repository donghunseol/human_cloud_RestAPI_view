package com.example.project_v2.user;

import com.example.project_v2._core.errors.exception.Exception401;
import com.example.project_v2._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository;

    public User sameCheck(String username) {
        User user = userJPARepository.findByUsername(username)
                .orElseThrow(() -> new Exception401("사용할 수 있는 아이디입니다"));
        return user;
    }

    @Transactional
    public User join(UserRequest.JoinDTO reqDTO) {
        return userJPARepository.save(reqDTO.toEntity());
    }

    public User login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return sessionUser;
    }

    @Transactional
    public User update(Integer id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        user.setUsername(reqDTO.getUsername());
        user.setPassword(reqDTO.getPassword());
        user.setTel(reqDTO.getTel());
        user.setEmail(reqDTO.getEmail());
        user.setAddress(reqDTO.getAddress());

        // 이미지 파일 넣기
        MultipartFile imgFile = reqDTO.getImage(); // 이미지 파일 데이터 저장
        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename(); // 이미지 파일 오리지널 이름

        // 파일 저장 위치 설정
        Path imgPath = Paths.get("./src/main/resources/static/images/" + imgFilename);

        try {
            Files.write(imgPath, imgFile.getBytes());
            user.setImage(imgFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}

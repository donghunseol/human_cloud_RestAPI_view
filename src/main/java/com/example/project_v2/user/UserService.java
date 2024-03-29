package com.example.project_v2.user;

import com.example.project_v2._core.errors.exception.Exception401;
import com.example.project_v2._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public User join(UserRequest.JoinDTO reqDTO){
        return userJPARepository.save(reqDTO.toEntity());
    }

    public User login(UserRequest.LoginDTO reqDTO){
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
        user.setImage(reqDTO.getImage());
        return user;
    }
}

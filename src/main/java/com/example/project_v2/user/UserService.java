package com.example.project_v2.user;

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
}

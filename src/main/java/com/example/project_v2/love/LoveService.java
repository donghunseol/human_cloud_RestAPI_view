package com.example.project_v2.love;

import com.example.project_v2._core.errors.exception.Exception401;
import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2.board.BoardJPARepository;
import com.example.project_v2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoveService {
    private final LoveJPARepository loveJPARepository;
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public Love save(LoveRequest.SaveDTO reqDTO, User sessionUser){
        Love love = loveJPARepository.save(reqDTO.toEntity(sessionUser));
        return love;
    }
}

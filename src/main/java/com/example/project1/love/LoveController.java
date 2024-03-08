package com.example.project1.love;

import com.example.project1._core.util.ApiUtil;
import com.example.project1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LoveController {

    private final LoveRepository loveRepository;
    private final HttpSession session;

    @DeleteMapping("/api/love/{id}")
    public ApiUtil<?> delete(@PathVariable Integer id, HttpServletRequest response){
//         인증
        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) {
//            response.setStatus(401);
//            return new ApiUtil<>(401, "인증안됨");
//        }

//         2. 권한 체크
        Love love = loveRepository.findById(id);
//        if(love.getUserId() != sessionUser.getId()){
//            response.setStatus(403);
//            return new ApiUtil<>(403, "권한없음");
//        }

        loveRepository.deleteById(id);
        return new ApiUtil<>(null);
    }

    @PostMapping("/api/love")
    public ApiUtil<?> save(@RequestBody LoveRequest.SaveDTO requestDTO, HttpServletRequest response){
        // 인증
        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) {
//            response.setStatus(401);
//            return new ApiUtil<>(401, "인증안됨");
//        }

        Love love = loveRepository.save(requestDTO, sessionUser.getId());
        return new ApiUtil<>(love);
    }
}

package com.example.project1.scrap;

import com.example.project1._core.util.ApiUtil;
import com.example.project1.user.User;
import com.example.project1.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScrapController {
    private final ScrapRepository scrapRepository;
    private final HttpSession session;

    @DeleteMapping("/api/scrap/{id}")
    public ApiUtil<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Scrap scrap = scrapRepository.findById(id);

        scrapRepository.deleteById(scrap.getId());
        return new ApiUtil<>(null);
    }

    @PostMapping("/api/scrap")
    public ApiUtil<?> save(@RequestBody ScrapRequest.UserDTO userDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Scrap scrap = null;
        if (sessionUser.getRole() == 0) {
            ScrapResponse.DetailDTO scrapDTO = scrapRepository.findScrap(sessionUser.getId(), sessionUser.getId());
            System.out.println("이거 어딧냐 " + scrapDTO);
            session.setAttribute("scrap", scrapDTO);
            scrap = scrapRepository.userSave(sessionUser.getId(), userDTO);
        }




        return new ApiUtil<>(scrap);
    }
}

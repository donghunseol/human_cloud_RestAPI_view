package com.example.project_v2.scrap;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ScrapController {
    private final ScrapRepository scrapRepository;
    private final HttpSession session;

    @DeleteMapping("/api/scrap/{id}")
    public ApiUtil<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");


        scrapRepository.deleteById(id);
        return new ApiUtil<>(null);
    }

    @PostMapping("/api/scrap")
    public ApiUtil<?> save(@RequestBody ScrapRequest.UserDTO userDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapResponse.DetailDTO scrapDTO = scrapRepository.findScrap(sessionUser.getId(), sessionUser.getId());
        session.setAttribute("scrap", scrapDTO);
        Scrap scrap = scrapRepository.save(sessionUser.getId(), userDTO);

        return new ApiUtil<>(scrap);
    }
}
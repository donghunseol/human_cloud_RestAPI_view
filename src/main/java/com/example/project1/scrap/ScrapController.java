package com.example.project1.scrap;

import com.example.project1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ValueConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ScrapController {
    private final HttpSession session;
    private final ScrapRepository scrapRepository;

    @GetMapping("/scrap/{id}")
    public String index(@PathVariable Integer id, HttpServletRequest request) {
        List<ScrapResponse.ScrapDTO> scrap_list = scrapRepository.findById(id);
        request.setAttribute("scrap_list", scrap_list);

        boolean owner = false; // 페이지 주인 여부 체크
        User session_user = (User) request.getAttribute("session_user");

        if(session_user != null){
            if (id == session_user.getId()){
                owner = true;
            }
        }

        System.out.println(scrap_list);
        request.setAttribute("owner", owner);

        return "scrap/main";
    }
}

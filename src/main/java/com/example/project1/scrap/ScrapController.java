package com.example.project1.scrap;

import com.example.project1.user.User;
import com.example.project1.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ValueConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ScrapController {
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    @GetMapping("/scrap/{id}")
    public String index(@PathVariable Integer id) {
        User user = (User) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapDTO> scrapList = scrapRepository.findById(id, user.getRole());
        session.setAttribute("scrapList", scrapList);
        System.out.println(scrapList);

        return "/scrap/main";
    }

    @PostMapping("/scrap/{id}/save")
    public String save(@PathVariable(name = "id") Integer id, ScrapRequest.UserDTO UserDTO, ScrapRequest.CompanyDTO companyDTO){
        User user = (User) session.getAttribute("sessionUser");

        // false 는 스크랩 비활성화 / true 는 스크랩 활성화
        Boolean isScrap = null;
        session.setAttribute("isScrap", isScrap);

        if(isScrap != false){
            if (user.getRole() == 0) {
                System.out.println(UserDTO);
                scrapRepository.userSave(user.getId(), UserDTO);
            }else if(user.getRole() == 1) {
                System.out.println(companyDTO);
                scrapRepository.companySave(user.getId(), companyDTO);
            }
        }

        return "redirect:/notice/"+id;
    }

    @PostMapping("/scrap/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id){
        scrapRepository.deleteById(id);
        return "redirect:/scrap/"+id;
    }
}

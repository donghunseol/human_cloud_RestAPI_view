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
    public String index(@PathVariable Integer id, HttpServletRequest request) {
        List<ScrapResponse.ScrapDTO> scrapList = scrapRepository.findById(id);
        request.setAttribute("scrapList", scrapList);
        System.out.println(scrapList);
//
//        boolean owner = false; // 페이지 주인 여부 체크
//        User session_user = (User) request.getAttribute("session_user");
//
//        if(session_user != null){
//            if (1 == session_user.getId()){
//                owner = true;
//            }
//        }

        System.out.println(scrapList);
        //request.setAttribute("owner", owner);

        return "/scrap/main";
    }

    @PostMapping("/scrap/{id}/save")
    public String save(@PathVariable(name = "id") Integer id, ScrapRequest.IndividualDTO individualDTO, ScrapRequest.CompanyDTO companyDTO){
        System.out.println("test1");
        User user = (User) session.getAttribute("sessionUser");
        System.out.println("test1-1");
        if (user.getRole() == 0) {
            System.out.println("test2");
            System.out.println(individualDTO);
            scrapRepository.individualSave(individualDTO);
        }else if(user.getRole() == 1) {
            System.out.println("test3");
            System.out.println(companyDTO);
            scrapRepository.companySave(companyDTO);
        }

        System.out.println("test4");
        return "redirect:/notice/"+id;
    }


    @PostMapping("/scrap/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id){
        // System.out.println(id);
        scrapRepository.deleteById(id);
        return "redirect:/notice/"+id;
    }
}

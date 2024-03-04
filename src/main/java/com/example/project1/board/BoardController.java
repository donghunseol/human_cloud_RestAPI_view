package com.example.project1.board;

import com.example.project1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    @DeleteMapping("/board/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request){

        // 인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 권한
        Board board = boardRepository.findByIdForDelete(id);
//        if (board.getUser_id() != sessionUser.getId()) {
//            request.setAttribute("status", 403);
//            request.setAttribute("msg", "게시글 삭제 권한이 없습니다");
//            return "error/40x";
//        }

        boardRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request){

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
//        System.out.println(requestDTO);

//        if(requestDTO.getTitle().length() > 30){
//            request.setAttribute("status", 400);
//            request.setAttribute("msg", "제목의 길이가 30자를 초과해서는 안되요");
//            return "error/40x";
//        }

        boardRepository.save(requestDTO, sessionUser.getId());

        return "redirect:/";
    }

    @GetMapping("/board" )
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "board/main";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {

        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null){
            return "redirect:/loginForm";
        }else {
            return "board/saveForm";
        }
    }

    @GetMapping("/board/updateForm")
    public String updateForm(){
        return "board/updateForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id , HttpServletRequest request) {

        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);

        User sessionUser = (User) session.getAttribute("sessionUser");

        boolean pageOwner;
        if (sessionUser == null){
            pageOwner = false;
        } else {
            int boardId = responseDTO.getUser_id();
            int loginUserId = sessionUser.getId();
            pageOwner = boardId == loginUserId;
        }

        request.setAttribute("board", responseDTO);
        request.setAttribute("pageOwner", pageOwner);
        return "board/detail";
    }
}


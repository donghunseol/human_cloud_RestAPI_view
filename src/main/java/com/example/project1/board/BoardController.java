package com.example.project1.board;

import com.example.project1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO){
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) {
//            return "redirect:/loginForm";
//        }

//        Board board = boardRepository.findId(id);
//
//        if (board.getUser_id() != sessionUser.getId()){
//            return "error/403";
//        }

        boardRepository.update(requestDTO, id);

        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request){

        // 인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 권한
        Board board = boardRepository.findId(id);
//        if (board.getUser_id() != sessionUser.getId()) {
//            request.setAttribute("status", 403);
//            request.setAttribute("msg", "게시글 삭제 권한 X");
//            return "error/40x";
//        }

        boardRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request){

        //인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
//        System.out.println(requestDTO);


          //권한
//        if(requestDTO.getTitle().length() > 30){
//            request.setAttribute("status", 400);
//            request.setAttribute("msg", "제목의 길이가 50자를 초과해서는 안되요");
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

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request ){
//        //인증
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if(sessionUser == null){
//            return "redirect:/loginForm";
//        }
//
//        //권한
//        Board board = boardRepository.findId(id);
//
//        if(board.getUser_id() != sessionUser.getId()){
//            return "error/403";
//        }
//        //담기
//        request.setAttribute("board",board);

        return "board/updateForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id , HttpServletRequest request) {

        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);

        User sessionUser = (User) session.getAttribute("sessionUser");

        boolean pageOwner;
        if (sessionUser == null){
            pageOwner = false;
        } else {
            int boardId = responseDTO.getUserId();
            int loginUserId = sessionUser.getId();
            pageOwner = boardId == loginUserId;
        }

        request.setAttribute("board", responseDTO);
        request.setAttribute("pageOwner", pageOwner);
        return "board/detail";
    }
}


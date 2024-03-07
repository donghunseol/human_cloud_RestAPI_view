package com.example.project1.board;

import com.example.project1.reply.ReplyRepository;
import com.example.project1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

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
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "") String keyword) {

        if (keyword.isBlank()) {
            List<Board> boardList = boardRepository.findAll(page);
            // 전체 페이지 개수
            int count = boardRepository.count().intValue();

            int namerge = count % 3 == 0 ? 0 : 1;
            int allPageCount = count / 3 + namerge;

            request.setAttribute("boardList", boardList);
            request.setAttribute("first", page == 0);
            request.setAttribute("last", allPageCount == page + 1);
            request.setAttribute("prev", page - 1);
            request.setAttribute("next", page + 1);
            request.setAttribute("keyword", "");
        } else {
            List<Board> boardList = boardRepository.findAll(page, keyword);
            // 전체 페이지 개수
            int count = boardRepository.count(keyword).intValue();

            int namerge = count % 3 == 0 ? 0 : 1;
            int allPageCount = count / 3 + namerge;

            request.setAttribute("boardList", boardList);
            request.setAttribute("first", page == 0);
            request.setAttribute("last", allPageCount == page + 1);
            request.setAttribute("prev", page - 1);
            request.setAttribute("next", page + 1);
            request.setAttribute("keyword", keyword);
        }

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

        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO boardDTO = boardRepository.findByIdWithUser(id);
        boardDTO.isBoardOwner(sessionUser);

        List<BoardResponse.ReplyDTO> replyDTOList = replyRepository.findByBoardId(id ,sessionUser);
        request.setAttribute("board", boardDTO);
        request.setAttribute("replyList", replyDTOList);

        return "board/detail";
    }
}


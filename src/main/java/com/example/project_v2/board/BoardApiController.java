package com.example.project_v2.board;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.love.LoveRepository;
import com.example.project_v2.love.LoveResponse;
import com.example.project_v2.reply.ReplyRepository;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardApiController {

    private final BoardJPARepository boardJPARepository;
    private final HttpSession session;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final LoveRepository loveRepository;

    // 게시글 수정
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return ResponseEntity.ok(new ApiUtil<>(null));
        }

        Board board = boardRepository.findId(id);

        boardRepository.update(requestDTO, id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, HttpServletRequest request){

        // 인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return ResponseEntity.ok(new ApiUtil<>(null));
        }

        // 권한
        Board board = boardRepository.findId(id);

        boardRepository.deleteById(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 작성
    @PostMapping("/api/boards")
    public ResponseEntity<?> save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request){

        //인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return ResponseEntity.ok(new ApiUtil<>(null));
        }

        boardRepository.save(requestDTO, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 목록 보기
    @GetMapping("/boards")
    public ResponseEntity<?> index(HttpServletRequest request,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "") String keyword) {

        if (keyword.isBlank()) {
            List<Board> boardList = boardRepository.findAll(page);
            // 전체 페이지 개수
            int count = boardRepository.count().intValue();

            int namerge = count % 7 == 0 ? 0 : 1;
            int allPageCount = count / 7 + namerge;

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

            int namerge = count % 7 == 0 ? 0 : 1;
            int allPageCount = count / 7 + namerge;

            request.setAttribute("boardList", boardList);
            request.setAttribute("first", page == 0);
            request.setAttribute("last", allPageCount == page + 1);
            request.setAttribute("prev", page - 1);
            request.setAttribute("next", page + 1);
            request.setAttribute("keyword", keyword);
        }

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 상세 보기
    @GetMapping("/boards/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id , HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO boardDTO = boardRepository.findByIdWithUser(id);
        boardDTO.isBoardOwner(sessionUser);

        List<BoardResponse.ReplyDTO> replyDTOList = replyRepository.findByBoardId(id ,sessionUser);
        request.setAttribute("board", boardDTO);
        request.setAttribute("replyList", replyDTOList);

        if(sessionUser == null){
            LoveResponse.DetailDTO loveDetailDTO = loveRepository.findLove(id);
            request.setAttribute("love", loveDetailDTO);
        }else {
            LoveResponse.DetailDTO loveDetailDTO = loveRepository.findLove(id, sessionUser.getId());
            request.setAttribute("love", loveDetailDTO);
        }

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}


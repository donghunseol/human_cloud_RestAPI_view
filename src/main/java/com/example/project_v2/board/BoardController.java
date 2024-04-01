package com.example.project_v2.board;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;


    // 게시글 수정
    @PutMapping("/boards/{id}/updateTest")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.update(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(board));
    }

    // 게시글 삭제
    @DeleteMapping("/boards/{id}/test")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User sessionUSer = (User) session.getAttribute("sessionUser");
        boardService.delete(id, sessionUSer.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 작성
    @PostMapping("/boards/test")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 로그인 여부 체크
        Board board = boardService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    // 게시글 목록 보기
    @GetMapping("/boards")
    public ResponseEntity<?> index() {
        List<BoardResponse.MainDTO> respDTO = boardService.boardMain();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 게시글 상세 보기
    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}


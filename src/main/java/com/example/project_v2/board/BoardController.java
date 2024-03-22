package com.example.project_v2.board;

import com.example.project_v2._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardJPARepository boardJPARepository;
    private final BoardService boardService;
    private final HttpSession session;

    // 게시글 수정
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable int id){
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 작성
    @PostMapping("/api/boards")
    public ResponseEntity<?> save(){
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 목록 보기
    @GetMapping("/boards")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 상세 보기
    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        BoardResponse.DetailDTO reqDTO = boardService.boardDetail(id);
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }
}


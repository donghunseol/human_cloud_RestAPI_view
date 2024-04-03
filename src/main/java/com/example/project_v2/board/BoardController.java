package com.example.project_v2.board;

import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    // 게시글 수정화면 이동
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        //인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/users/login-form";
        }
        Board board = boardService.findById(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }

    // 게시글 수정
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.update(id, sessionUser.getId(), reqDTO);
        return "redirect:/boards/" + id + "/detail";
    }

    // 게시글 삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id) {
        //인증
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/users/login-form";
        }
        boardService.delete(id, sessionUser.getId());
        return "redirect:/board";
    }

    // 게시글 작성화면 이동
    @GetMapping("/board/save-form")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/users/login-form";
        } else {
            return "board/save-form";
        }
    }

    // 게시글 작성
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 로그인 여부 체크
        boardService.save(reqDTO, sessionUser);
        return "redirect:/board";
    }

    // 게시글 목록 보기
    @GetMapping("/board")
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "id") String sortBy,
                        @RequestParam(defaultValue = "desc") String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BoardResponse.MainDTO> boardPage = boardService.boardMain(pageable);

        List<BoardResponse.MainDTO> respDTO = boardPage.getContent();

        request.setAttribute("boardList", respDTO);
        request.setAttribute("pages", IntStream.rangeClosed(0, boardPage.getTotalPages() - 1).boxed().collect(Collectors.toList()));

        // 현재 페이지 계산
        int currentPage = page + 1;
        request.setAttribute("currentPage", currentPage);

        request.setAttribute("size", size);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("direction", direction);
        request.setAttribute("hasPrevious", boardPage.hasPrevious());
        request.setAttribute("previousPage", boardPage.hasPrevious() ? boardPage.getNumber() - 1 : 0);
        request.setAttribute("hasNext", boardPage.hasNext());
        request.setAttribute("nextPage", boardPage.hasNext() ? boardPage.getNumber() + 1 : boardPage.getTotalPages() - 1);

        return "board/main";
    }

    // 게시글 상세 보기
    @GetMapping("/boards/{id}/detail")
    public String detail(HttpServletRequest request, @PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id, sessionUser);
        request.setAttribute("boardDetail", respDTO);
        return "board/detail";
    }
}


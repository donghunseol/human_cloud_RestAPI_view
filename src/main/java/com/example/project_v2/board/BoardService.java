package com.example.project_v2.board;

import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;


    @Transactional
    public Board update(Integer boardId, Integer sessionUserId, BoardRequest.UpdateDTO reqDTO){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if(sessionUserId != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return board;
    } // 더티 체킹

    @Transactional
    public void delete(int boardId, Integer sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId);
    }

    @Transactional
    public Board save(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return board;
    }

    // 게시글 상세보기
    public BoardResponse.DetailDTO boardDetail(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        return new BoardResponse.DetailDTO(board, sessionUser);
    }

    public List<BoardResponse.MainDTO> 게시글목록보기() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> boardList = boardJPARepository.findAll(sort);
        return boardList.stream().map(board -> new BoardResponse.MainDTO(board)).toList();
    }

}

package com.example.project_v2.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    // 게시글 상세보기
    public BoardResponse.DetailDTO boardDetail(int boardId) {
        Board board = boardJPARepository.findByIdJoinUser(boardId);
        return new BoardResponse.DetailDTO(board);
    }

}

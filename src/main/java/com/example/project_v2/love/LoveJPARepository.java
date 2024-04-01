package com.example.project_v2.love;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoveJPARepository extends JpaRepository<Love, Integer> {
    @Query("select l from Love l where l.board.id = :board_id and  l.user.id = :user_id")
    Optional<Love> findLoveByBoardIdAndUserId(@Param("board_id") Integer boardId, @Param("user_id") Integer userId);
}

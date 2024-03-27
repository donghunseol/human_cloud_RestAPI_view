package com.example.project_v2.notice;

import com.example.project_v2.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticeJPARepository extends JpaRepository<Notice, Integer> {

    @Query("select n from Notice n join fetch n.user u where n.id = :id")
    Optional<Notice> findByIdJoinUser(@Param("id") Integer id);
}

package com.example.project1.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {
}

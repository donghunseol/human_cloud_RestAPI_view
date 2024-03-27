package com.example.project_v2.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {
    @Query("select r from Resume r join fetch r.user u where r.id = :id")
    Optional<Resume> findByIdJoinUser(@Param("id") int id);
}

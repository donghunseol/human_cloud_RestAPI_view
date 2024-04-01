package com.example.project_v2.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("SELECT a FROM Apply a JOIN FETCH a.resume WHERE a.id = :id")
    Optional<Apply> findById(@Param("id") Integer id);

}

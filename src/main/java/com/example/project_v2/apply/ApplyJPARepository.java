package com.example.project_v2.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    void deleteByNoticeId(Integer noticeId);

    // 지원에서 이력서를 조인 시켜 조회함
    @Query("SELECT a FROM Apply a JOIN FETCH a.resume WHERE a.id = :id")
    Optional<Apply> findById(@Param("id") Integer id);

    @Query("SELECT a FROM Apply a JOIN FETCH a.user u JOIN FETCH a.notice n WHERE a.user.id = :userId")
    List<Apply> findAppliesByUserId(@Param("userId") Integer userId);
}

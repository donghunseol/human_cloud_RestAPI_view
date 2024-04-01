package com.example.project_v2.scrap;

import com.example.project_v2.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScrapJPARepository extends JpaRepository<Scrap, Integer> {
    @Query("select s from Scrap s join fetch s.resume r where s.resume.id = :rid and s.user.id = :uid")
    Optional<Scrap> findByResumeIdAndUserId(@Param("rid") int rid, @Param("uid") int uid);

    @Query("select s from Scrap s join fetch s.notice n where s.notice.id = :nid and s.user.id = :uid")
    Optional<Scrap> findByNoticeIdAndUserId(@Param("nid") int nid, @Param("uid") int uid);
}

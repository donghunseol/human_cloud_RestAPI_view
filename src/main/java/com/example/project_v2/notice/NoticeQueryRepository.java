package com.example.project_v2.notice;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NoticeQueryRepository {
    private final EntityManager em;
}

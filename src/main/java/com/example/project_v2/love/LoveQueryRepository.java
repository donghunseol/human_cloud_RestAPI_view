package com.example.project_v2.love;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LoveQueryRepository {
    private final EntityManager em;
}

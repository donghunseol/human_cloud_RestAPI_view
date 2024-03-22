package com.example.project_v2.resume;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResumeQueryRepository {
    private final EntityManager em;
}

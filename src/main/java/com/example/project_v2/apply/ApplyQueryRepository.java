package com.example.project_v2.apply;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ApplyQueryRepository {
    private final EntityManager em;
}

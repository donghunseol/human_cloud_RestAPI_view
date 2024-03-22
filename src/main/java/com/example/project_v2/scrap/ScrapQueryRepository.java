package com.example.project_v2.scrap;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ScrapQueryRepository {
    private final EntityManager em;
}

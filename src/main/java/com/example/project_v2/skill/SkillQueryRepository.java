package com.example.project_v2.skill;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SkillQueryRepository {
    private final EntityManager em;
}

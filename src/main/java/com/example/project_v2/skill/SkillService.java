package com.example.project_v2.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillJPARepository skillJPARepository;
}

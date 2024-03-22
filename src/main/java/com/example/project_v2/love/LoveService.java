package com.example.project_v2.love;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoveService {
    private final LoveJPARepository loveJPARepository;
}

package com.example.project_v2.love;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class LoveJPARepositoryTest {

    @Autowired
    private LoveJPARepository loveJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findLoveByBoardIdAndUserId_test() {
        // given
        Integer boardId = 1;
        Integer userId = 1;

        // when
        Optional<Love> love = loveJPARepository.findLoveByBoardIdAndUserId(boardId, userId);
        System.out.println("findLoveByBoardIdAndUserId_test/id : " + love.get().getId());
        System.out.println("findLoveByBoardIdAndUserId_test/user : " + love.get().getUser());
        // System.out.println("findLoveByBoardIdAndUserId_test/board : " + love.get().getBoard());

        // then

    }
}

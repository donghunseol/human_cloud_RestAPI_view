package com.example.project_v2.board;

import org.assertj.core.api.Assertions;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;


    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();

        List<Board> boardList = boardJPARepository.findAll();

        // then
        Assertions.assertThat(boardList.size()).isEqualTo(14);
    }

    @Test
    public void findAll_test(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        //when
        List<Board> boardList = boardJPARepository.findAll();
        //then
        System.out.println("findAll_test : " + boardList);
    }

}

package com.example.project_v2.board;

import com.example.project_v2._core.errors.exception.Exception403;
import com.example.project_v2._core.errors.exception.Exception404;
import com.example.project_v2._core.util.ApiUtil;
import com.example.project_v2.user.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;


    @Test
    public void save_test(){
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        Assertions.assertThat(board.getId()).isEqualTo(16);
    }

    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);


        // then
        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            Assertions.assertThat(board.getTitle()).isEqualTo("안녕하십니까");
        }


    }

    @Test
    public void delete_test(){
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

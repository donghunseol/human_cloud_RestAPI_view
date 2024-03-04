package com.example.project1.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    // 삭제 위한 부분 조회


    // 부분 조회
    public BoardResponse.DetailDTO findById(int idx) {
        Query query = em.createNativeQuery("select b.id, b.title, b.content, b.user_id, u.username from board_tb b inner join user_tb u on b.user_id = u.id where b.id = ?");
        query.setParameter(1, idx);

        Object[] row = (Object[]) query.getSingleResult();

        Integer id = (Integer) row[0];
        Integer userId = (Integer) row[1];
        String title = (String) row[2];
        String content = (String) row[3];
        String username = (String) row[4];

//        System.out.println("id: " +id);
//        System.out.println("userId: " +userId);
//        System.out.println("title: " +title);
//        System.out.println("content: " +content);
//        System.out.println("username: " +username);

        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO();
        responseDTO.setId(id);
        responseDTO.setUser_id(userId);
        responseDTO.setTitle(title);
        responseDTO.setContent(content);
        responseDTO.setUsername(username);

        return responseDTO;
    }

    // 전체 조회
    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc" , Board.class);
        return query.getResultList();
    }

    // 저장
    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO, int userId){

        Query query = em.createNativeQuery("insert into board_tb(title, content, user_id, created_at) values(?,?,?,now())");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, userId);

        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(){

    }

    // 수정
    @Transactional
    public void update(){

    }
}

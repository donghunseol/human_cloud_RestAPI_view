package com.example.project1.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    // 부분 조회
    public User findById(Integer id) {
        Query query = em.createNativeQuery("select * from user_tb where id = ?");
        query.setParameter(1, id);
        return (User) query.getSingleResult();
    }

    public void findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username = ?");
        query.setParameter(1, username);
        query.executeUpdate();
    }


    // 전체 조회
    public List<User> findAll() {
        Query query = em.createNativeQuery("select * from user_tb", User.class);
        List<User> userList = query.getResultList();
        return userList;
    }

    // 저장
    @Transactional
    public void save(UserRequest.JoinDTO requestDTO) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, name, tel, birth, address, email, role, created_at) values (?, ?, ?, ?, ?, ?, ?, 1, now())");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getName());
        query.setParameter(4, requestDTO.getTel());
        query.setParameter(5, requestDTO.getBirth());
        query.setParameter(6, requestDTO.getAddress());
        query.setParameter(7, requestDTO.getEmail());

        query.executeUpdate();
    }

    // 삭제
    @Transactional
    public void deleteById(UserResponse.DTO responseDTO) {
        Query query = em.createNativeQuery("delete from user_tb where id=?");
        query.setParameter(1, responseDTO.getId());
        query.executeUpdate();
    }


    //버튼으로 구현.

    // 수정
    @Transactional
    public void update(UserRequest.JoinDTO requsetDTO, int id) {
        Query query = em.createNativeQuery("UPDATE user_tb set address=? where id =?", User.class);
        query.setParameter(1, requsetDTO.getAddress());
        query.setParameter(2, id);
        query.executeUpdate();
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username=? and password=?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        return (User) query.getSingleResult();
    }
}

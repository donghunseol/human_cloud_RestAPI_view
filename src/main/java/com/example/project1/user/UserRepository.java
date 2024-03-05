package com.example.project1.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    // 부분 조회
    public User findById(Integer id) {
        Query query=em.createNativeQuery("select * from user_tb where id = ?");
        query.setParameter(1, id);
        return (User) query.getSingleResult();
    }

    public void findByUsername(String username) {
        Query query=em.createNativeQuery("select * from user_tb where username = ?");
        query.setParameter(1, username);
        query.executeUpdate();
    }


    // 전체 조회
    public void findAll() {

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
    public void deleteById() {

    }

    // 수정
    @Transactional
    public void update() {

    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
          Query query = em.createNativeQuery("select * from user_tb where username=? and password=?",User.class);
          query.setParameter(1, requestDTO.getUsername());
          query.setParameter(2, requestDTO.getPassword());

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            throw new RuntimeException("id 혹은 password를 찾을 수 없습니다.");
        }

    }
}

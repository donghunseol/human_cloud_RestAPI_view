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

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username = ?", User.class);
        query.setParameter(1, username);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    // 전체 조회
    public List<User> findAll() {
        Query query = em.createNativeQuery("select * from user_tb", User.class);
        List<User> userList = query.getResultList();
        return userList;
    }

    public List<User> findAllWithDelete() {
        Query query = em.createNativeQuery("select * from user_tb", User.class);
        List<User> userList = query.getResultList();
        return userList;
    }

    // 저장
    @Transactional
    public void save(UserRequest.JoinDTO requestDTO) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, name, tel, birth, address, email, role, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, now())");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getName());
        query.setParameter(4, requestDTO.getTel());
        query.setParameter(5, requestDTO.getBirth());
        query.setParameter(6, requestDTO.getAddress());
        query.setParameter(7, requestDTO.getEmail());
        query.setParameter(8, requestDTO.getRole());

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
    public void update(UserRequest.UpdateDTO requestDTO, Integer id){
        Query query = em.createNativeQuery("UPDATE user_tb set username=?, password=?, name=?, tel =?, birth=?, email=?,address=?, image=?, created_at=now() where id =?",User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getName());
        query.setParameter(4, requestDTO.getTel());
        query.setParameter(5, requestDTO.getBirth());
        query.setParameter(6, requestDTO.getEmail());
        query.setParameter(7, requestDTO.getAddress());
        query.setParameter(8, requestDTO.getImage());
        query.setParameter(9, id);
        query.executeUpdate();
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
          Query query = em.createNativeQuery("select * from user_tb where username=? and password=?",User.class);
          query.setParameter(1, requestDTO.getUsername());
          query.setParameter(2, requestDTO.getPassword());
          User user = (User) query.getSingleResult();
          return  user;
    }
}

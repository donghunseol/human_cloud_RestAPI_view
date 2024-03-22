package com.example.project_v2.user;

import com.example.project_v2._core.errors.exception.Exception401;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByUsernameAndPassword_test(){
        // given
        String username = "ssar";
        String password = "1234";

        // when
        User user = userJPARepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
        Assertions.assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    public void join_test(){
        // given
        User user = User.builder()
                .username("happy")
                .password("12345")
                .name("행복")
                .tel("01011112222")
                .birth("20001010")
                .email("happy@nate.com")
                .address("부산광역시 연제구 거제동")
                .role(1)
                .build();

        // when
        userJPARepository.save(user);
        System.out.println("join_test/user : " + user);

        // then
    }
}

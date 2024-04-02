package com.example.project_v2.user;

import com.example.project_v2._core.errors.exception.Exception404;
import org.assertj.core.api.Assertions;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Import({UserService.class})
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private UserService userService;

    @Test
    public void update_test() {
        // given
        int id = 1;
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setUsername("happy");
        reqDTO.setPassword("12345");
        reqDTO.setTel("01012345678");
        reqDTO.setEmail("happy@nate.com");
        reqDTO.setAddress("경남 창원시 성산구 상남동");
        //reqDTO.setImage("/Images/happy.png");

        // when
        User user = userJPARepository.findById(id).orElseThrow(() -> new Exception404("존재하지 않는 회원 입니다"));
        System.out.println("update_test/after : " + user);
        userService.update(id, reqDTO);
        System.out.println("update_test/before : " + user);

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("happy");
    }
}

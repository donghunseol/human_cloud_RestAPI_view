package com.example.project1.notice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(NoticeRepository.class)
public class NoticeControllerTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void delete_test(){
        int id = 2;

        noticeRepository.deleteById(id);

        noticeRepository.findAll().forEach(System.out::println);


    }

}

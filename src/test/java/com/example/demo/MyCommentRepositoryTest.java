package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(false)
public class MyCommentRepositoryTest {

    @Autowired
    MyCommentRepository myCommentRepository;

    @Test
    public void crudTest22(){

        MyComment myComment = new MyComment();
        myComment.setTitle("hello test name");
        myComment.setBigTest(new Integer(100));
        myCommentRepository.save(myComment);


        List<MyComment> all = myCommentRepository.findByTitleContainsIgnoreCase("HELLO");
        assertThat(all.size()).isEqualTo(1);

        Page<MyComment> page = myCommentRepository.findByBigTestGreaterThan(1000, PageRequest.of(0,10));
        assertThat(page.getNumberOfElements()).isEqualTo(1);

//        long count = myCommentRepository.count();
//        assertThat(count).isEqualTo(1);

//        Optional<MyComment> byID = myCommentRepository.findById(100L);
//        assertThat(byID).isEmpty();
//        MyComment c1 = byID.orElse(new MyComment(101L));
//        MyComment myComment1 = byID.orElseThrow(IllegalAccessError::new);

    }
}
package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(false)
public class MyCommentRepositoryTest {

    @Autowired
    MyCommentRepository myCommentRepository;

    @Test
    public void crudTest22() throws ExecutionException, InterruptedException {

        MyComment myComment = new MyComment();
        myComment.setTitle("ccccchello test name");
        myComment.setBigTest(new Integer(100));
        myCommentRepository.save(myComment);

        MyComment myComment1 = new MyComment();
        myComment1.setTitle("aaahello test name");
        myComment1.setBigTest(new Integer(101));
        myCommentRepository.save(myComment1);

        MyComment myComment2 = new MyComment();
        myComment2.setTitle("bbbbhellobbbb test name");
        myComment2.setBigTest(new Integer(1021));
        myCommentRepository.save(myComment2);


        List<MyComment> all = myCommentRepository.findByTitleContainsIgnoreCase("HELLO");
        assertThat(all.size()).isEqualTo(3);

        Page<MyComment> page = myCommentRepository.findByBigTestGreaterThanOrderByTitleDesc(10, PageRequest.of(0,10));
        page.getContent().forEach(s-> {
            System.out.println("-----------------------");
            System.out.println(s.getTitle());
        });
        assertThat(page.getNumberOfElements()).isEqualTo(3);

        Stream<MyComment> p = myCommentRepository.findByBigTestGreaterThanOrderByTitleAsc(10, PageRequest.of(0,10));
        assertThat(p.findFirst().get().getBigTest()).isEqualTo(101);

        ListenableFuture<List<MyComment>> f =  myCommentRepository.findByBigTestLessThanOrderByTitleAsc(105, PageRequest.of(0,10));
//        f.get().forEach(s->{
//            log.warn("&&&&&&&&&&&&&&&&&&&&&&&&&");
//            log.warn(s.getTitle());
//        });

        f.addCallback(new ListenableFutureCallback<List<MyComment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.warn("failure...................");
            }

            @Override
            public void onSuccess(@Nullable List<MyComment> myComments) {
                log.warn("success....................");
            }
        });


//        long count = myCommentRepository.count();
//        assertThat(count).isEqualTo(1);

//        Optional<MyComment> byID = myCommentRepository.findById(100L);
//        assertThat(byID).isEmpty();
//        MyComment c1 = byID.orElse(new MyComment(101L));
//        MyComment myComment1 = byID.orElseThrow(IllegalAccessError::new);

    }
}
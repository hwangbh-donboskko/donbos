package com.dsl.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LessonBookRepositoryTest {

    @Autowired
    LessonBookRepository lessonBookRepository;

    @Autowired
    DslAccountRepository dslAccountRepository;

    @Test
    public void test1(){


        DslAccount dslAccount = new DslAccount();
        dslAccount.setLesson("math");
        DslAccount dslAccount1 = dslAccountRepository.save(dslAccount);


        LessonBook lessonBook = new LessonBook();
        lessonBook.setDslAccount(dslAccount1);
        LessonBook lessonBook1 = lessonBookRepository.save(lessonBook);

        Optional<LessonBook> op = lessonBookRepository.findById(lessonBook1.getId());
        System.out.println(op.get().getDslAccount().getLesson());



    }


}
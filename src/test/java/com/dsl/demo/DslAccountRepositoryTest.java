package com.dsl.demo;

import com.example.demo.QAccount;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Rollback(false)
public class DslAccountRepositoryTest {

    @Autowired
    DslAccountRepository dslAccountRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void query1() {
        DslAccount dslAccount = new DslAccount();
        dslAccount.setLesson("geometry");
        dslAccountRepository.save(dslAccount);

        List<DslAccount> list = dslAccountRepository.findByLessonStartsWith("geo");
        assertThat(list.size()).isEqualTo(1);


    }

    @Test
    public void query2() {

        DslAccount dslAccount = new DslAccount();
        dslAccount.setLesson("geometry");
        dslAccountRepository.save(dslAccount);

        List<DslAccount> list = dslAccountRepository.findByLesson("geometry");
        assertThat(list.size()).isEqualTo(1);


    }
    @Test
    public void query3() {

        DslAccount dslAccount = new DslAccount();
        dslAccount.setLesson("geometry33333");
        dslAccount.setFName("donbos");
        dslAccountRepository.save(dslAccount);

        DslAccount dslAccount1 = new DslAccount();
        dslAccount1.setLesson("statistics");
        dslAccount1.setFName("donbos");
        dslAccountRepository.save(dslAccount1);


        List<DslAccount> list = dslAccountRepository.findByFName("donbos", JpaSort.unsafe("LENGTH(lesson)"));
        System.out.println(list.get(0).getLesson());
        System.out.println(list.get(1).getLesson());
        assertThat(list.size()).isEqualTo(2);


    }

    @Test
    public void update1(){

        DslAccount dslAccount = new DslAccount();
        dslAccount.setLesson("geometry33333");
        dslAccount.setFName("donbos");
        dslAccountRepository.save(dslAccount);

        int cnt = dslAccountRepository.updateLesson("math", dslAccount.getId());
        assertThat(cnt).isEqualTo(1);
        assertThat(dslAccountRepository.findByLesson("math").size()).isEqualTo(1);



    }

    @Test
    public void crud() {

        DslAccount dslAccount = new DslAccount();
        dslAccount.setFName("mike");
        dslAccount.setLName("Hwang");
        dslAccountRepository.save(dslAccount);

        QDslAccount qDslAccount = QDslAccount.dslAccount;
        Predicate predicate = qDslAccount.fName.containsIgnoreCase("Mike")
                .and(qDslAccount.lName.startsWith("Hwang"));

        System.out.println("-----------------------------------------------");
        Optional<DslAccount> one = dslAccountRepository.findOne(predicate);
        log.warn(one.toString());
        System.out.println("---------------------------------------------");
        assertThat(one).isEmpty();
    }

    @Test
    public void crud1() {

    }

    @Test
    public void save() {

        DslAccount dslAccount = new DslAccount();
        dslAccount.setLesson("geometry");
        DslAccount dslAccount1 = dslAccountRepository.save(dslAccount);//persist

        assertThat(entityManager.contains(dslAccount)).isTrue();
        assertThat(entityManager.contains(dslAccount1)).isTrue();
        assertThat(dslAccount == dslAccount1);

        DslAccount dslAccount2 = new DslAccount();
        dslAccount2.setId(dslAccount.getId());
        dslAccount2.setLesson("statistics");
        DslAccount dslAccount3 = dslAccountRepository.save(dslAccount2); //merge

        assertThat(entityManager.contains(dslAccount3)).isTrue();
        assertThat(entityManager.contains(dslAccount2)).isFalse();
        assertThat(dslAccount2 == dslAccount3).isFalse();


    }
}
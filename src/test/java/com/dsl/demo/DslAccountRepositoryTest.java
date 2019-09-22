package com.dsl.demo;

import com.example.demo.QAccount;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Rollback(false)
public class DslAccountRepositoryTest {

    @Autowired
    DslAccountRepository dslAccountRepository;

    @Test
    public void crud(){

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
    public void crud1(){

    }

}
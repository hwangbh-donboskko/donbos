package com.example.demo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("mike3");
        account.setPassword("aaaa");

        Address address = new Address();


        Study study = new Study();
        study.setName("spring jpa");

        account.setStudy(study);

        study.setAccount(account);
        account.getStudies().add(study);

        System.out.println("test....");

/*
        entityManager.persist(account);
*/

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

      /*  try(Connection connection = dataSource.getConnection()){
            System.out.println(connection);
            String URL = connection.getMetaData().getURL();
            System.out.println(URL);
            String User = connection.getMetaData().getUserName();
            System.out.println(User);

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE ACCOUNT22(" +
                    "ID INTEGER NOT NULL," +
                    "NAME VARCHAR(255)," +
                    "PRIMARY KEY(ID))";

            //statement.executeUpdate(sql);
        }

        jdbcTemplate.execute("INSERT INTO ACCOUNT22 VALUES(2, 'mike')");*/




    }
}

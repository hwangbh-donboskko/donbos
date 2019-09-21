package com.example.demo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

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

        TypedQuery<Post> query = entityManager.createQuery("select p from Post as p", Post.class);
        List<Post> resultList = query.getResultList();
        resultList.forEach(System.out::println);

       /* Post post = new Post();
        post.setTitle("jpa lesson");

        MyComment myComment = new MyComment();
        myComment.setName("this is comment test");

        MyComment myComment1 = new MyComment();
        myComment1.setName("this 222222222 jpa");

        MyComment myComment2 = new MyComment();
        myComment2.setName("this 3333333333333 jpa");


        post.setMyComment(myComment);
        post.setMyComment(myComment1);
        post.setMyComment(myComment2);
*/
//        Session session = entityManager.unwrap(Session.class);
////        session.save(post);
//
//        Post pTest = session.get(Post.class, 1L);
//        pTest.getCommentSet().forEach(s->{
//            System.out.println(s.getName());
//        });
//        session.delete(pTest);
//        session.save(myComment);
//        session.save(myComment1);

       /* System.out.println("aaaaaaaaaaaaaaaaa");
        session.save(post);
        System.out.println("bbbbbbbbbb");
       // session.close();
        System.out.println("ccccccccccccccc");
        session.evict(post);
        System.out.println("ddddddddddddd");
        session.clear();
        System.out.println("eeeeeeeeeeeeeee");
*/
      /*  Account account = new Account();
        account.setUsername("mike3");
        account.setPassword("aaaa");

        Address address = new Address();


        Study study = new Study();
        study.setName("spring jpa");

        account.setStudy(study);

        study.setAccount(account);
        account.getStudies().add(study);

        System.out.println("test....");*/

/*
        entityManager.persist(account);
*/

      /*  Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

        Account aTest = session.load(Account.class, account.getId());
        aTest.setUsername("test name");
        System.out.println("----------------------");
        System.out.println(aTest.getUsername());*/

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

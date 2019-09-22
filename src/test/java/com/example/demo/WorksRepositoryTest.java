package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest// 데이타 관련된 Bean만 등록해주는 슬라이싱 테스트이므로 WorksListener을 빈으로 등록하기 위해
//아래와 같이 import를 하였음
@Import(WorksRepositoryTestConfig.class)
public class WorksRepositoryTest {

    @Autowired
    WorksRepository worksRepository;


    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void event(){

        Works works = new Works();
        works.setTitle("event test");
        WorksEventPublisher worksEventPublisher = new WorksEventPublisher(works);
        applicationContext.publishEvent(worksEventPublisher);
    }

    @Test
    public void eventTest11(){
        Works works = new Works();
        works.setTitle("event testkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        worksRepository.save(works.publish()); //이떄 event가 발생하도록 jpa가 해두었음 위와 같은 event 처리 메써드 구현 안해도 됨
   }


    @Test
    public void test1(){
        Works works = new Works();
        works.setTitle("aTitle");
        works.setContent("aContentaContentaContentaContentaContent");
        worksRepository.save(works);


        Works works22 = new Works();
        works22.setTitle("aTitle22222");
        works22.setContent("222222aContentaContentaContentaContentaContent");

        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertThat(worksRepository.Contains(works22)).isTrue();
        worksRepository.save(works22);
        assertThat(worksRepository.Contains(works22)).isTrue();





//
//
//        List<Works> works1 = worksRepository.findAll();
//        works1.forEach(System.out::println);
//        assertThat(works1.size()).isEqualTo(2);
//
//        worksRepository.findMyWorks();
//        worksRepository.delete(works22);
//        worksRepository.flush();
//
//        works1 = worksRepository.findAll();
//        works1.forEach(System.out::println);
//        assertThat(works1.size()).isEqualTo(1);


    }
}
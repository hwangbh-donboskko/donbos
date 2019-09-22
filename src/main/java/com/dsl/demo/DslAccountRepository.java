package com.dsl.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 안 붙여도 됨
public interface DslAccountRepository extends JpaRepository<DslAccount, Long>, QuerydslPredicateExecutor<DslAccount> {

    List<DslAccount> findByLessonStartsWith(String lesson);

    List<DslAccount> findByLesson(String lesson);

//    @Query("select p from DslAccount as p where p.fName = ?1")
//    List<DslAccount> findByFName(String fName, Sort sort);


//    @Query("select p from DslAccount as p where p.fName = :fName")
//    List<DslAccount> findByFName(@Param("fName") String keyword, Sort sort);

    //spEL
    @Query("select p from #{#entityName} as p where p.fName = :fName")
    List<DslAccount> findByFName(@Param("fName") String keyword, Sort sort);

    @Modifying(clearAutomatically = true)
    @Query("update DslAccount p set lesson= ?1 where id= ?2")
    int updateLesson(String lesson, Long id);


}

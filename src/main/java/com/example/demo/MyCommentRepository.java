package com.example.demo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;


import java.util.List;

public interface MyCommentRepository extends MyReposInterface<MyComment,Long> {

  //  @Query(value = "select c from MyComment c", nativeQuery=true)
    List<MyComment> findByTitleContainsIgnoreCase(String keyword);

    Page<MyComment> findByBigTestGreaterThan(int bigTest, Pageable pageable);







}

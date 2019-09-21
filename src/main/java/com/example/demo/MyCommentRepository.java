package com.example.demo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;


import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@EnableAsync
public interface MyCommentRepository extends MyReposInterface<MyComment,Long> {

  //  @Query(value = "select c from MyComment c", nativeQuery=true)
    List<MyComment> findByTitleContainsIgnoreCase(String keyword);

    Page<MyComment> findByBigTestGreaterThanOrderByTitleDesc(int bigTest, Pageable pageable);

    Stream<MyComment> findByBigTestGreaterThanOrderByTitleAsc(int bigTest, Pageable pageable);

    @Async
    ListenableFuture<List<MyComment>> findByBigTestLessThanOrderByTitleAsc(int bigTest, Pageable pageable);





}

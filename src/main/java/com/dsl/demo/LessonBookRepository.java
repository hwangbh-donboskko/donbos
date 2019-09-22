package com.dsl.demo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.Optional;

public interface LessonBookRepository extends JpaRepository<LessonBook, Long> {

//    @EntityGraph("LessonBook.dslAccount" type=EntityGraph.EntityGraphType.LOAD)
//    Optional<LessonBook> getById(Long id);

//    @EntityGraph(attributePaths = {"dslAccount"})
//    Optional<LessonBook> getById(Long id);

}



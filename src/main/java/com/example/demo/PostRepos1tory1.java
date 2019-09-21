package com.example.demo;

import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepos1tory1 extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContains(String title, Pageable pageable);

}

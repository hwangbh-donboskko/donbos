package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Rollback(false)
public class PostRepos1tory1Test {

    @Autowired
    PostRepos1tory1 postRepos1tory1;

    @Test
    public void crudRepos(){
        //Given
        Post post = new Post();
        post.setTitle("h2 test with jpa test");
        assertThat(post.getId()).isNull();
//When
        Post newPost = postRepos1tory1.save(post);
//Then
        System.out.println("test process");
        assertThat(newPost.getId()).isNotNull();

        List<Post> posts = postRepos1tory1.findAll();
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);
        //When
        Page<Post> page = postRepos1tory1.findAll(PageRequest.of(0,10));
        assertThat(page.getTotalElements()).isEqualTo(1L);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        page = postRepos1tory1.findByTitleContains("jpa", PageRequest.of(0,10));
        assertThat(page.getTotalElements()).isEqualTo(1L);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);
    }

}
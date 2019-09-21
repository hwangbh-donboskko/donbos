package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Post add(Post post){
        entityManager.persist(post);
        return post;
    }

    public void delete(Post post){
        entityManager.remove(post);

    }
    public List<Post> findAll(){
        return entityManager.createQuery("select p from Post as p", Post.class)
                .getResultList();
    }

}

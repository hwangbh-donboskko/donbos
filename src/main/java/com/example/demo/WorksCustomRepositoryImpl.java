package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class WorksCustomRepositoryImpl implements WorksCustomRepository<Works> {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Works> findMyWorks() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa   findMyWorks");
        return entityManager.createQuery("select p from Works AS p ", Works.class).getResultList();
    }

    @Override
    public void delete(Works Entity) {
        System.out.println("delete works..................");
        entityManager.remove(Entity);

    }
}
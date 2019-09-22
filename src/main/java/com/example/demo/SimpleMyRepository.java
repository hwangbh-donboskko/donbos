package com.example.demo;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class SimpleMyRepository<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID>{


    private  EntityManager entityManager;

    public SimpleMyRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public boolean Contains(T Entity) {
        return entityManager.contains(Entity);
    }
}

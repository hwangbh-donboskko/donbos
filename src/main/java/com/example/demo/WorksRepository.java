package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface WorksRepository extends JpaRepository<Works, Long> , WorksCustomRepository<Works>{

    public interface WorksRepository extends BaseRepository<Works, Long>{

    }

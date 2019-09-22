package com.example.demo;

import java.util.List;

public interface WorksCustomRepository<T> {

    public List<Works> findMyWorks();

    void delete(T Entity);



}
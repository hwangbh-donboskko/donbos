package com.dsl.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DslAccountController {

    @Autowired
    DslAccountRepository dslAccountRepository;
    //생성자가 하나일 경우 빈으로 자동등록받을수 있다

    //domain class converter에 의해 id <-> entity entity <-> entity 가 되어진다
    @GetMapping("/accounts/{id}")
    public String getA(@PathVariable("id") Long id){
        Optional<DslAccount> getId = dslAccountRepository.findById(id);
        DslAccount dslAccount = getId.get();
        return dslAccount.getUserName();
    }

    @GetMapping("/lesson/{id}")
    public String getAA(@PathVariable("id") DslAccount dslAccount){
        return dslAccount.getLesson();
    }

    @GetMapping("/lessons")
    public Page<DslAccount> getLessons(Pageable pageable){
        return dslAccountRepository.findAll(pageable);
    }


    @GetMapping("/accounts")
    public PagedResources getAccounts(Pageable pageable, PagedResourcesAssembler assembler){
        Page<DslAccount> all = dslAccountRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        return assembler.toResource(all);
    }


}

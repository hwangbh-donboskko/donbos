package com.dsl.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

//@Repository // 안 붙여도 됨
public interface DslAccountRepository extends JpaRepository<DslAccount, Long>, QuerydslPredicateExecutor<DslAccount> {
}

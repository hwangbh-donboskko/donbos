package com.dsl.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DslAccountRepository extends JpaRepository<DslAccount, Long> {
}

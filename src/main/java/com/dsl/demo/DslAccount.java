package com.dsl.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Slf4j
@NamedQuery(name = "DslAccount.findByLesson" , query = "select p from DslAccount as p where p.lesson = ?1")
public class DslAccount {

    @Id@GeneratedValue
    private Long id;

    private String userName;

    private String fName;

    private String lName;

    private String lesson;




}

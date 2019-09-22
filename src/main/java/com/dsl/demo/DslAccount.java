package com.dsl.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Slf4j
public class DslAccount {

    @Id@GeneratedValue
    private Long id;

    private String userName;

    private String fName;

    private String lName;

    private String lesson;


}

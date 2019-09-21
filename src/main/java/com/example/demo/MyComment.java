package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class MyComment {

    @Id@GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Post post;

}

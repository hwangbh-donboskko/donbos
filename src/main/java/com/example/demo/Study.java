package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Study {

    @Id@GeneratedValue
    private Long ID;

    private String name;

    @ManyToOne
    private Account account;

}

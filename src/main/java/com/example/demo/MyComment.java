package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MyComment {

    @Id@GeneratedValue
    private Long id;

    private String name;

    private String title;

    private Integer bigTest;

    @ManyToOne
    private Post post;

    public MyComment(Long id, String name, Post post) {
        this.id = id;
        this.name = name;
        this.post = post;
    }

    public MyComment(long l) {
        this.id = l;
    }
}

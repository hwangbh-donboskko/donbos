package com.dsl.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@NamedEntityGraph(name="LessonBook.dslAccount"
//, attributeNodes = @NamedAttributeNode("dslAccount"))
public class LessonBook {

    @Id@GeneratedValue
    private Long Id;

    private String book;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private DslAccount dslAccount;

}

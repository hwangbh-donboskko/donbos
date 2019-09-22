package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class Works extends AbstractAggregateRoot<Works> {

    @Id @GeneratedValue
    private Long ID;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Override
    public String toString() {


        return "Works{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                '}';
    }
    public  Works publish(){
        this.registerEvent(new WorksEventPublisher(this));
        return this;

    }
}

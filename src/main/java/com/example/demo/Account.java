package com.example.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Account {

    @Id@GeneratedValue
    private long id;
    //private Long id;
//Wrapper를 주로 사용

    @Column
    private String username;

    @Column
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Transient//DB Table로 매핑하고 싶지 않을떄 사용하는 키
    private String No;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name="street", column = @Column(name="home_street"))
    )
    private Address address;

}

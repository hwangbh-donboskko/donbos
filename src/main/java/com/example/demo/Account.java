package com.example.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "account")
    private Set<Study> studies = new HashSet<>();

    public void setStudy(Study study) {
        study.setAccount(this);
        this.getStudies().add(study);
    }

    public void removeStudy(Study study) {
        study.setAccount(null);
        this.getStudies().remove(study);
    }

}


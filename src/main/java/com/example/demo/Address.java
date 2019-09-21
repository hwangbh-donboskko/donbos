package com.example.demo;

import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
public class Address {

    private String street;

    private String road;

    private String zipcode;
}

package com.example.demo;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String road;

    private String zipcode;
}

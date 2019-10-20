package com.company;

import java.io.Serializable;

public abstract class Person implements Serializable {

    private String name;
    private long socialSecurityNumber;

    public Person(String name, long socialSecurityNumber){
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public abstract void showInfo();
}

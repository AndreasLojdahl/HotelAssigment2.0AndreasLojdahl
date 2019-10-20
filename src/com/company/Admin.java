package com.company;

import java.io.Serializable;

public class Admin extends Person implements Serializable {

    public Admin(String name, long socialSecurityNumber){
        super(name,socialSecurityNumber);
    }

    @Override
    public void showInfo() {
        System.out.println("Hi I am a Admin, " + getName());
    }

    @Override
    public String toString() {
        return String.format("Hi! I am a Admin, " + getName());
    }
}

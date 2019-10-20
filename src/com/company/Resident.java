package com.company;

import java.io.Serializable;

public class Resident extends Person implements Serializable {

    public Resident(String name, long socialSecurityNumber){

        super(name,socialSecurityNumber);
    }

    @Override
    public void showInfo() {
        System.out.println("Hi I am a Resident, " + getName());
    }

    @Override
    public String toString() {
        return String.format("Hi I Am A Resident, Andy");
    }
}

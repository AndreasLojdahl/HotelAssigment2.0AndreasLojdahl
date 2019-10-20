package com.company;

import java.io.Serializable;

public class Employee extends Person implements Serializable {

    public Employee(String name, long socialSecurityNumber){

        super(name,socialSecurityNumber);
    }

    @Override
    public void showInfo() {
        System.out.println("Hi I am a Employee, " + getName());
    }

    @Override
    public String toString() {
        return String.format("Hi! I Am A Employee, " + getName());
    }
}

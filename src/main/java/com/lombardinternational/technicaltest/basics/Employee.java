package com.lombardinternational.technicaltest.basics;

public class Employee extends Person {

    private String dpt;

    public Employee(String firstname, String lastname, String dpt) {
        super(firstname, lastname);
        this.dpt = dpt;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

}

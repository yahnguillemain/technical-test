package com.lombardinternational.technicaltest.springmvc.controllers;

public class CounterService {

    private int count = 0;

    public void increment() {
        ++count;
    }

    public int getValue() {
        return count;
    }
}

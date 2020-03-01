package com.al.project.sysco.Model;


public abstract class Measure {

    public String EXCHANGE_NAME;

    public Measure(String name) {
        this.EXCHANGE_NAME = name;
    }

    public abstract  double getValue();
}
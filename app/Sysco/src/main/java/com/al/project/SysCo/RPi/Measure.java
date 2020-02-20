package com.al.project.SysCo.RPi;


import java.util.Random;

public abstract class Measure {

    public String EXCHANGE_NAME;

    public Measure(String name) {
        this.EXCHANGE_NAME = name;
    }

    public abstract  double getValue();
}
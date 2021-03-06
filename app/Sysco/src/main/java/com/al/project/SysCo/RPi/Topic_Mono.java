package com.al.project.SysCo.RPi;


import java.util.Random;

public class Topic_Mono extends Topic {

    private final double min = 0.0;
    private final double max = 2.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Topic_Mono(String name) {
        super(name);
    }
}
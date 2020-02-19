package com.al.project.SysCo.RPi;


import java.util.Random;

public class Topic_Temp extends Topic {

    private final double min = 15.0;
    private final double max = 38.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Topic_Temp(String name) {
        super(name);
    }
}
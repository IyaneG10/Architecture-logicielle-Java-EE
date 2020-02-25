package com.al.project.SysCo.RPi;


import java.util.Random;

public class Measure_Mono extends Measure {

    private final double min = 0.0;
    private final double max = 2.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return Math.round(value*100.0)/100.0;
    }


   public Measure_Mono(String name) {
        super(name);
    }
}
package com.al.project.SysCo.RPi;


import java.util.Random;

public class Measure_Temp extends Measure {

    private final double min = 15.0;
    private final double max = 38.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return Math.round(value*100.0)/100.0;
    }


   public Measure_Temp(String name) {
        super(name);
    }
}
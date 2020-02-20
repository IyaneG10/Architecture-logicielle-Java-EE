package com.al.project.SysCo.RPi;


import java.util.Random;

public class Measure_Press extends Measure {

    private final double min = 500.0;
    private final double max = 1200.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }

   public Measure_Press(String name) {
        super(name);
    }
}
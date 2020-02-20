package com.al.project.SysCo.RPi;


import java.util.Random;

public class Measure_Humid extends Measure {

    private final int min = 25;
    private final int max = 70;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Measure_Humid(String name) {
        super(name);
    }
}
package com.al.project.sysco.Model;


import java.util.Random;

public class Measure_Humid extends Measure {

    private final int min = 25;
    private final int max = 70;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return Math.round(value*100.0)/100.0;
    }


   public Measure_Humid(String name) {
        super(name);
    }
}
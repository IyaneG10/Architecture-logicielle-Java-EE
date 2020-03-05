package com.al.project.sysco.Model;


import java.util.Random;

public class Measure_PartFi extends Measure {

    private final double min = 10.0;
    private final double max = 40.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return Math.round(value*100.0)/100.0;
    }


   public Measure_PartFi(String name) {
        super(name);
    }
}
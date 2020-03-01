package com.al.project.sysco.Model;


import java.util.Random;

public class Measure_Oxy extends Measure {

    private final double min = 15.0;
    private final double max = 35.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return Math.round(value*100.0)/100.0;
    }


   public Measure_Oxy(String name) {
        super(name);
    }
}
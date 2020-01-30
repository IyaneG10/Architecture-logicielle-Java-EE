package com.al.project.SysCo.RPi;


import java.util.Random;

public class Topic_Oxy extends Topic {

    private final double min = 15.0;
    private final double max = 35.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Topic_Oxy(String name) {
        super(name);
    }
}
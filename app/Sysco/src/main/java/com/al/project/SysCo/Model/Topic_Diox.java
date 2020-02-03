package com.al.project.SysCo.Model;


import java.util.Random;

public class Topic_Diox extends Topic {

    private final double min = 0.0;
    private final double max = 3.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Topic_Diox(String name) {
        super(name);
    }
}
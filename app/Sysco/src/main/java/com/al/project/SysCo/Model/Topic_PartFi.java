package com.al.project.SysCo.Model;


import java.util.Random;

public class Topic_PartFi extends Topic {

    private final double min = 10.0;
    private final double max = 40.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Topic_PartFi(String name) {
        super(name);
    }
}
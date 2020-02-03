package com.al.project.SysCo.Model;


import java.util.Random;

public class Topic_Press extends Topic {

    private final double min = 500.0;
    private final double max = 1200.0;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }

   public Topic_Press(String name) {
        super(name);
    }
}
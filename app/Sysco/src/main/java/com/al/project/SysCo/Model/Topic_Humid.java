package com.al.project.SysCo.Model;


import java.util.Random;

public class Topic_Humid extends Topic {

    private final int min = 25;
    private final int max = 70;
    private double value;

    public double getValue() {
        Random r = new Random();
        value = min + r.nextDouble() * (max - min);
        return value;
    }


   public Topic_Humid(String name) {
        super(name);
    }
}
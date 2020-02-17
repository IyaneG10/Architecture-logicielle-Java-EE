package com.al.project.SysCo.RPi;


import java.util.Random;

public abstract class Topic{

    public String EXCHANGE_NAME;

    public Topic(String name) {
        this.EXCHANGE_NAME = name;
    }

    public abstract  double getValue();
}
package com.al.project.SysCo.Model;


import java.util.Random;

public abstract class Topic{

    public String topicName;

    public Topic(String name) {
        this.topicName = name;
    }

    public abstract  double getValue();
}
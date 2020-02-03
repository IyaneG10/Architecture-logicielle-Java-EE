package com.al.project.SysCo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Data {

    public Long getId() {
        return id;
    }

    public int getRpiId() {
        return rpiId;
    }

    public boolean isState() {
        return state;
    }

    public String getTopicName() {
        return topicName;
    }

    public double getTopicValue() {
        return topicValue;
    }

    public String getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRpiId(int rpiId) {
        this.rpiId = rpiId;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicValue(double topicValue) {
        this.topicValue = topicValue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    private Long id;
    private int rpiId;
    private boolean state;
    private String topicName;
    private double topicValue;
    private String date;


    public String ToXMLString(){
        return String.format("<RPi><Id>%s</Id><Topic><Name>%s</Name><Value>%s</Value></Topic><State>%s</State><Date>%s</Date></RPi>",
                rpiId,
                topicName,
                topicValue,
                state,
                date);
    }
}

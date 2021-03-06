package com.al.project.SysCo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "data") // si le nom de la table n'est pas précisée, celui de la classe sera pris par défau
public class Data {


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

    public Date getDate() {
        return date;
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

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    private int rpiId;
    private boolean state;
    private String topicName;
    private double topicValue;
    private Date date;


    public Data toData(String dataString){

        return this;
    }


    public String toString() {
        try {
            return String.format("<RPi><Id>%s</Id><Topic><Name>%s</Name><Value>%s</Value></Topic><State>%s</State><Date>%s</Date></RPi>",
                    this.getRpiId(),
                    this.getTopicName(),
                    this.getTopicValue(),
                    this.isState(),
                    this.getDate());
        }
        catch (Exception e){
            return null;
        }
    }
}

package com.al.project.sysco.Service;

import com.al.project.sysco.Model.*;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Rpi extends   Thread{
    public long getId() {
        return id;
    }

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(6);

    private void setId(int id) {
        this.id = id;
    }

    private int id;

    private Publisher publisher;

    private boolean State() {
        // create random object and
        // get next boolean value
        return new Random().nextBoolean();

    }

    private static String getDate() {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateObj = new Date();
        return formatter.format(dateObj);
    }

    private String [] measureNameList = new String []{
            "Oxygene", "Monoxyde de carbone",
            "Dioxyde de carbone", "Temperature",
            "Humidite", "Pression atmospherique",
            "Particules fines"
    };

    private List<Measure> listMeasureName = new ArrayList<>();

    public Rpi(int rpiId){
        setId(rpiId);
        MeasureSetting();
        publisher = new Publisher();
    }

    private void MeasureSetting(){

        listMeasureName.add(new Measure_Oxy(measureNameList[0]));
        listMeasureName.add(new Measure_Mono(measureNameList[1]));
        listMeasureName.add(new Measure_Diox(measureNameList[2]));
        listMeasureName.add(new Measure_Temp(measureNameList[3]));
        listMeasureName.add(new Measure_Humid(measureNameList[4]));
        listMeasureName.add(new Measure_Press(measureNameList[5]));
        listMeasureName.add(new Measure_PartFi(measureNameList[6]));
    }

    Runnable  sendMeasure_Oxy = () -> {
        SendTopic(0,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendMeasure_Mono = () -> {
        SendTopic(1,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendMeasure_Diox = () -> {
        SendTopic(2,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendMeasure_Temp = () -> {
        SendTopic(3,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendMeasure_Humid = () -> {
        SendTopic(4,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendMeasure_Press = () -> {
        SendTopic(5,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendMeasure_PartFi = () -> {
        SendTopic(6, "Rpi.DataBase.Room."+Long.toString(id));
    };

    private String CreateFakeMeasures(int measureNumber){
        try {

            Data data =  new Data();
            data.setDate(getDate());
            data.setRpiId(id);
            data.setState(State());
            if( ! data.isState() )                                               //if sensor is off, then don't send it's value
                data.setMeasureValue(0.00);
            else
                data.setMeasureValue(listMeasureName.get(measureNumber).getValue());
            data.setMeasureName(measureNameList[measureNumber]);

            return data.dataToJsonstring();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void SendTopic(int measureNumber, String routingKey){

        try {
            String message = CreateFakeMeasures(measureNumber);
            if(Objects.nonNull(message)) {                                                                   // Avoids filling DB with values when sensor if off
                publisher.publish("RPiTopics", routingKey, message);
                System.out.println(" [+] " + message);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            executor.scheduleAtFixedRate(sendMeasure_Oxy, 0, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendMeasure_Mono, 5, 60, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendMeasure_Diox, 10, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendMeasure_Temp, 15, 60, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendMeasure_Humid, 20, 3600, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendMeasure_Press, 25, 3600, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendMeasure_PartFi, 30, 1800, TimeUnit.SECONDS);                   // 30 minutes
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void stopTasks() {
        executor.shutdownNow();
    }
}
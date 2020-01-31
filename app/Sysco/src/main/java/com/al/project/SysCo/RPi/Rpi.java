package com.al.project.SysCo.RPi;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

public class Rpi {
    private Integer id;
    private Topic topic;
    private Publisher publisher = new Publisher();

    private String [] topicListName = {
        "Oxygene","Monoxyde de carbone",
        "Dioxyde de carbone","Temperature",
        "Humidite","Pression atmospherique",
        "Particules fines"
    } ;


    private List<Topic> topicList = new ArrayList<Topic>();

    public Rpi(Integer id){
        this.id = id;
        TopicSetting();
    }

    private void TopicSetting(){

        topicList.add(new Topic_Oxy(topicListName[0]));
        topicList.add(new Topic_Mono(topicListName[1]));
        topicList.add(new Topic_Diox(topicListName[2]));
        topicList.add(new Topic_Temp(topicListName[3]));
        topicList.add(new Topic_Humid(topicListName[4]));
        topicList.add(new Topic_Press(topicListName[5]));
        topicList.add(new Topic_PartFi(topicListName[6]));
    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Oxy(){
        try {
            publisher.publish(topicListName[0],"DB",id+);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Mono(){

    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Diox(){

    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Temp(){

    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Humid(){

    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Press(){

    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_PartFi(){

    }
}

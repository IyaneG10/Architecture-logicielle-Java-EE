package com.al.project.SysCo.RPi;

import java.util.ArrayList;
import java.util.List;

public class Rpi {
    private Integer id;
    private Topic topic;

    private String [] topicListName = {
        "Oxygene","Monoxyde de carbone",
        "Dioxyde de carbone","Temperature",
        "Humidite","Pression atmospherique",
        "Particules fines"
    } ;

    private List<Topic> topicList = new ArrayList<Topic>();

    public Rpi(Integer id){
        this.id = id;
        TopicSetting();;
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
}

package com.al.project.SysCo.RPi;

import com.al.project.SysCo.Model.*;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Rpi {
    private Integer id;
    private Topic topic;
    private static String date;
    private boolean state;
    private Publisher publisher = new Publisher();

    public boolean getState() {
        // create random object
        Random random = new Random();
        // get next next boolean value
        boolean state = random.nextBoolean();

        return state;
    }

    public static String getDate() {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateObj = new Date();
        date = formatter.format(dateObj);
        //System.out.println(date);
        return date;
    }

    private String [] topicListName = {
        "Oxygene","Monoxyde de carbone",
        "Dioxyde de carbone","Temperature",
        "Humidite","Pression atmospherique",
        "Particules fines"
    } ;


    private List<Topic> topicList = new ArrayList<>();

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
        SendTopic(0);
    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Mono(){
        SendTopic(1);
    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Diox(){
        SendTopic(2);
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Temp(){
        SendTopic(3);
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Humid(){
        SendTopic(4);
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Press(){
        SendTopic(5);
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_PartFi(){
        SendTopic(6);
    }

    private String XMLFormater(String[] elements){
        String xml = " <?xml version = \"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>"
            + "<RPi>"
                + "<Id>" + elements[0] + "</Id>"
                + "<Topic>"
                    + "<Name>" + elements[1] + "</Name>"
                    + "<Value>" + elements[2] + "</Value>"
                +"</Topic>"
                + "<State>" + elements[3] + "</State>"
                + "<Date>" + elements[4] + "</Date>"
            + "</RPi>";
        return xml;
    }

    private String CreateFakeTopics(int topicNumber){
        try {
            String [] elements = {
                    Integer.toString(id),
                    topicListName[topicNumber],
                    Double.toString(topicList.get(topicNumber).getValue()),
                    Boolean.toString(getState()),
                    ""
            };
            String topicMessage = XMLFormater(elements);
            return topicMessage;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void SendTopic(int topicNumber){

        try {
            String message = CreateFakeTopics(topicNumber);
            if(message!= "")
                publisher.publish(topicListName[topicNumber],"DB",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv){
    System.out.println(getDate());
    }
}

package com.al.project.SysCo.RPi;

import com.al.project.SysCo.Model.*;
import appConfig.SysCoConfig;
import com.al.project.SysCo.Service.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.*;

public class Rpi {
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private Topic topic;
    //private static String date;
    //private boolean state;


    @Autowired
    private static SysCoConfig appConfig;
    //private final Map<String, SysCoConfig> servers = new HashMap<>();
    //private YAMLConfig myConfig;

    public static void TestYML(){

        Map<String, SysCoConfig.Server> servers;
        servers = appConfig.getServers();
        //List<SysCoConfig.Sysco> serverList = appConfig.getServers();
        System.out.println("servers list: " + servers.get("rabbitmq"));
        //System.out.println("name: " + myConfig.getName());
        //System.out.println("servers: " + myConfig.getServers());
    }


    private Publisher publisher = new Publisher();

    private boolean getState() {
        // create random object
        Random random = new Random();
        // get next next boolean value
        boolean state = random.nextBoolean();

        return state;
    }

    private static String getDate() {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateObj = new Date();
        //date = formatter.format(dateObj);
        //System.out.println(date);
        return formatter.format(dateObj);
    }

    private String [] topicListName = new String []{
            "Oxygene", "Monoxyde de carbone",
            "Dioxyde de carbone", "Temperature",
            "Humidite", "Pression atmospherique",
            "Particules fines"
    };


    private List<Topic> topicList = new ArrayList<>();

    public Rpi(Integer rpiId){
        setId(rpiId);
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

    public void sendTopicResponse(){
        // Send all topics
        for(int i=0; i<topicList.size();i++)
            SendTopic(i);
    }

    private String CreateFakeTopics(int topicNumber){
        try {

            Data data =  new Data();
            data.setDate(getDate());
            data.setRpiId(id);
            data.setState(getState());
            data.setTopicName(topicListName[topicNumber]);
            data.setTopicValue(topicList.get(topicNumber).getValue());

            if( ! data.isState() || Objects.isNull(data))                                               //if sensor is off, then don't send it's value
                return null;

            return data.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void SendTopic(int topicNumber){

        try {
            String message = CreateFakeTopics(topicNumber);
            if(Objects.nonNull(message))                                                                                // Avoids filling DB with values when sensor if off
                publisher.publish(topicListName[topicNumber],"DB",message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] argv){
        System.out.println(getDate());
        TestYML();
    }
}

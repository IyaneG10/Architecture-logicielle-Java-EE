package com.al.project.SysCo.RPi;

import com.al.project.SysCo.Model.*;
import appConfig.SysCoConfig;
import com.al.project.SysCo.Service.Publisher;
import com.al.project.SysCo.Service.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.*;

public class Rpi extends  Object {
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

    /*
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
    */

    private Publisher publisher;
    private Subscriber subscriber;

    private boolean getState() {
        // create random object
        Random random = new Random();
        // get next next boolean value
        boolean state = random.nextBoolean();

        return state;
    }

    private static Date getDate() {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateObj = new Date();
        //date = formatter.format(dateObj);
        //System.out.println(date);
        return dateObj;//formatter.format(dateObj);
    }

    private String [] exchangeNameList = new String []{
            "Oxygene", "Monoxyde de carbone",
            "Dioxyde de carbone", "Temperature",
            "Humidite", "Pression atmospherique",
            "Particules fines"
    };


    private List<Topic> listExchangeName = new ArrayList<>();

    public Rpi(Integer rpiId){
        setId(rpiId);
        TopicSetting();
        publisher = new Publisher();
        subscriber = new Subscriber((Object) this);
        Subscribe();
    }


    private void TopicSetting(){

       // for (int i=0; i<listExchangeName.size();i++)
        listExchangeName.add(new Topic_Oxy(exchangeNameList[0]));
        listExchangeName.add(new Topic_Mono(exchangeNameList[1]));
        listExchangeName.add(new Topic_Diox(exchangeNameList[2]));
        listExchangeName.add(new Topic_Temp(exchangeNameList[3]));
        listExchangeName.add(new Topic_Humid(exchangeNameList[4]));
        listExchangeName.add(new Topic_Press(exchangeNameList[5]));
        listExchangeName.add(new Topic_PartFi(exchangeNameList[6]));
    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Oxy(){SendTopic(0,"Rpi.DataBase.Room."+Integer.toString(id));}

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Mono(){
        SendTopic(1,"Rpi.DataBase.Room."+Integer.toString(id));
    }

    @Scheduled(fixedRate = 30000)           //30 seconds
    private void sendTopic_Diox(){
        SendTopic(2,"Rpi.DataBase.Room."+Integer.toString(id));
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Temp(){
        SendTopic(3,"Rpi.DataBase.Room."+Integer.toString(id));
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Humid(){
        SendTopic(4,"Rpi.DataBase.Room."+Integer.toString(id));
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_Press(){
        SendTopic(5,"Rpi.DataBase.Room."+Integer.toString(id));
    }

    @Scheduled(fixedRate = 1800000)         //30 minutes
    private void sendTopic_PartFi(){
        SendTopic(6, "Rpi.DataBase.Room."+Integer.toString(id));
    }


    private  void Subscribe(){
        try {
            subscriber.Subscribe("User.Rpi.Room."+Integer.toString(id));
        }
        catch (Exception e){
            System.err.println("Bad usage subscribe method: \n"+e.getMessage());
        }
    }

    public void sendTopicResponseToRabbit(String request){                                                                      // Send response to the publish we subscribed

        System.out.println(" [x] Received Request: " + request);
        for(int i=0; i<listExchangeName.size();i++)                                                                            // Send all topics
            SendTopic(i,"Rpi.User.Room."+Integer.toString(id));
    }

    private String CreateFakeTopics(int topicNumber){
        try {

            Data data =  new Data();
            data.setDate(getDate());
            data.setRpiId(id);
            data.setState(getState());
            data.setTopicName(exchangeNameList[topicNumber]);
            data.setTopicValue(listExchangeName.get(topicNumber).getValue());

            if( ! data.isState() || Objects.isNull(data))                                               //if sensor is off, then don't send it's value
                return null;

            return data.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void SendTopic(int topicNumber, String routingKey){

        try {
            String message = CreateFakeTopics(topicNumber);
            if(Objects.nonNull(message))                                                                   // Avoids filling DB with values when sensor if off
                publisher.publish(exchangeNameList[topicNumber],routingKey,message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

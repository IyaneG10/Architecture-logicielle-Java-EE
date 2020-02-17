package com.al.project.SysCo.RPi;

import com.al.project.SysCo.Model.*;
import com.al.project.SysCo.Model.Publisher;
import com.al.project.SysCo.Model.Subscriber;


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

    private boolean State() {
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
        return formatter.format(dateObj);
    }

    private String [] exchangeNameList = new String []{
            "Oxygene", "Monoxyde de carbone",
            "Dioxyde de carbone", "Temperature",
            "Humidite", "Pression atmospherique",
            "Particules fines"
    };


    private List<Topic> listExchangeName = new ArrayList<>();

    public Rpi(int rpiId){
        setId(rpiId);
        TopicSetting();
        publisher = new Publisher();
        subscriber = new Subscriber((Object) this);
    }


    private void TopicSetting(){

        listExchangeName.add(new Topic_Oxy(exchangeNameList[0]));
        listExchangeName.add(new Topic_Mono(exchangeNameList[1]));
        listExchangeName.add(new Topic_Diox(exchangeNameList[2]));
        listExchangeName.add(new Topic_Temp(exchangeNameList[3]));
        listExchangeName.add(new Topic_Humid(exchangeNameList[4]));
        listExchangeName.add(new Topic_Press(exchangeNameList[5]));
        listExchangeName.add(new Topic_PartFi(exchangeNameList[6]));
    }

    Runnable  sendTopic_Oxy = () -> {
        SendTopic(0,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendTopic_Mono = () -> {
        SendTopic(1,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendTopic_Diox = () -> {
        SendTopic(2,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendTopic_Temp = () -> {
        SendTopic(3,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendTopic_Humid = () -> {
        SendTopic(4,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendTopic_Press = () -> {
        SendTopic(5,"Rpi.DataBase.Room."+Long.toString(id));
    };

    Runnable  sendTopic_PartFi = () -> {
        SendTopic(6, "Rpi.DataBase.Room."+Long.toString(id));
    };


    private  void Subscribe(){
        try {
            subscriber.Subscribe("User.Rpi.Room."+Long.toString(id));
        }
        catch (Exception e){
            System.err.println("Bad usage subscribe method: \n"+e.getMessage());
        }
    }

    public void sendTopicResponseToRabbit(String request){                                                                      // Send response to the publish we subscribed

        System.out.println(" [x] Received Request: " + request);
        for(int i=0; i<listExchangeName.size();i++)                                                                            // Send all topics
            SendTopic(i,"Rpi.User.Room."+Long.toString(id));
    }

    private String CreateFakeTopics(int topicNumber){
        try {

            DataTopic data =  new DataTopic();
            data.setDate(getDate());
            data.setRpiId(id);
            data.setState(State());
            data.setTopicName(exchangeNameList[topicNumber]);
            data.setTopicValue(listExchangeName.get(topicNumber).getValue());

            if( ! data.isState() || Objects.isNull(data))                                               //if sensor is off, then don't send it's value
                return null;

            return data.dataToJsonstring();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void SendTopic(int topicNumber, String routingKey){

        try {
            String message = CreateFakeTopics(topicNumber);
            if(Objects.nonNull(message)) {                                                                   // Avoids filling DB with values when sensor if off
                publisher.publish("RPiTopics"/*exchangeNameList[topicNumber]*/, routingKey, message);
                System.out.println(message);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            executor.scheduleAtFixedRate(sendTopic_Oxy, 0, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendTopic_Mono, 5, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendTopic_Diox, 10, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendTopic_Temp, 15, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendTopic_Humid, 20, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendTopic_Press, 25, 30, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(sendTopic_PartFi, 30, 30, TimeUnit.SECONDS);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public void stopTasks() {
        executor.shutdownNow();
        //this.isRunning = false;
    }
}

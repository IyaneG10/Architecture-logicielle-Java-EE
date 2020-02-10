package com.al.project.SysCo.Service;

import java.util.Objects;

public class RabbitMQService {



    private  Subscriber subscriber;
    private   Publisher publisher;

    public RabbitMQService() {
        this.publisher = new Publisher();
        this.subscriber =  new Subscriber(this);
    }


    public   void SubscribeToTopics(){
        try {
            subscriber.Subscribe("RPi.DataBase.Room.*");
            subscriber.Subscribe("RPi.User.Room.*");
        }
        catch (Exception e){
            System.err.println("Bad usage subscribe method: \n"+e.getMessage());
        }
    }


    public void GetRealTimeTopics(String EXCHANGE_NAME, String routingKey, String request){

        try {
            if(Objects.nonNull(request)) {                                                                              // Avoids filling DB with values when sensor if off
                publisher.publish(EXCHANGE_NAME,routingKey,request);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

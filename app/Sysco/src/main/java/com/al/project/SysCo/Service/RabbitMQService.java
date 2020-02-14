package com.al.project.SysCo.Service;

import java.util.Objects;
import java.util.Scanner;

public class RabbitMQService {

    /*public RabbitMQService() {
        this.publisher = new Publisher();
        this.subscriber =  new Subscriber(this);
    }*/


    public static  void SubscribeToTopics(){

         Subscriber subscriber =  new Subscriber(null);
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

            Publisher publisher = new Publisher();

            if(Objects.nonNull(request)) {                                                                              // Avoids filling DB with values when sensor if off
                publisher.publish(EXCHANGE_NAME,routingKey,request);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendTopicResponse(String routingKey, String response){
        System.out.println(" routingKey: " + routingKey + "\n response: " + response);

        ///if(routingKey.contains("RPi.User."))
            //SendDataToUser();
        //else
            //SendDataToDB();
    }



    public static void main(String[] args) {

        System.out.println(" START !\n To stop, type 'exit'");

        SubscribeToTopics();

        Scanner sc = new Scanner(System.in);
 
        String txt = sc.nextLine();

        while (! "exit".equalsIgnoreCase(txt)){
            System.out.println(" Wrong command. Type 'exit'");
            txt = sc.nextLine();
        }
    }
}

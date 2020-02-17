package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Publisher;
import com.al.project.SysCo.Model.Subscriber;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class RabbitMQService {

    /*public RabbitMQService() {
        this.publisher = new Publisher();
        this.subscriber =  new Subscriber(this);
    }*/

    /*
    private static final String EXCHANGE_NAME = "RPiTopics";

    //private static String EXCHANGE_NAME;
    private static final String hostIP = "193.48.57.166";
    private static final String username = "ima2a5-4fun";
    private static final String password = "glopglop";


    public static  void SubscribeToTopics(){

        Subscriber subscriber =  new Subscriber(null);
        try {
            Subscribe("RPi.DataBase.Room.*");
            Subscribe("RPi.User.Room.*");
        }
        catch (Exception e){
            System.err.println("Bad usage subscribe method: \n"+e.getMessage());
        }
    }*/


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




    public static void main(String[] args)  throws Exception{

        System.out.println(" START !\n To stop, type 'exit'");

        /*SubscribeToTopics();
        Subscriber subscriber =  new Subscriber(null);
        try {
            subscriber.Subscribe("RPi.DataBase.Room.*");
            subscriber.Subscribe("RPi.User.Room.*");
        }
        catch (Exception e){
            System.err.println("Bad usage subscribe method: \n"+e.getMessage());
        }*/



        final String EXCHANGE_NAME = "RPiTopics";

        ConnectionFactory factory = new ConnectionFactory();

        final String hostIP = "193.48.57.166";
        final String username = "ima2a5-4fun";
        final String password = "glopglop";


        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();


        channel.queueBind(queueName, EXCHANGE_NAME, "Rpi.DataBase.");


        System.out.println(" [*] Waiting for messages. To exit type 'exit'");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String response = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + response + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });


        /*Scanner sc = new Scanner(System.in);

        String txt = sc.nextLine();

        while (! "exit".equalsIgnoreCase(txt)){
            System.out.println(" Wrong command. Type 'exit'");
            txt = sc.nextLine();
        }*/
    }


   /*public static void Subscribe(String routingKey) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();


            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);


            System.out.println(" [*] Waiting for messages. To exit type 'exit'");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {

                String response = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + response + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
    }*/
}

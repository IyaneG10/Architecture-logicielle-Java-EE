package com.al.project.SysCo;


//
// import com.al.project.SysCo.api.XmlRPCServer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;


public class DataApplication {

    public static void main(String[] args) {

        try {

            System.out.println(" START !\n To stop, type 'exit'");

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

            channel.queueBind(queueName, EXCHANGE_NAME, "Rpi.DataBase.Room.*");
            channel.queueBind(queueName, EXCHANGE_NAME, "Rpi.User.Room.*");

            System.out.println(" [*] Waiting for messages. To exit type 'exit'");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {

                String response = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + response + "'");

                if(delivery.getEnvelope().getRoutingKey().contains("Rpi.User.Room.")){

                    //se connecter au controller

                    System.out.println("Envoyé au User");
                }
                else{
                    try {

                        //DataAPI.save(new Data().jsonstringToData(response));
                        System.out.println("Envoyé à la BDD");
                    }
                    catch (Exception ex){
                    }
                }
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
        catch (Exception exception) {
            System.err.println("WebClientServer: " + exception);
        }
    }
}


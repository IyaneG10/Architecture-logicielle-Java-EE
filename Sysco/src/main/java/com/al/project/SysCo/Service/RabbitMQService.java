package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.Publisher;
import com.al.project.SysCo.Repository.DataRepository;
import com.al.project.SysCo.api.DataAPI;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


//@SpringBootApplication
public class RabbitMQService {

    public static void GetRealTimeTopics(String idRpi, String request){

        try {
            String EXCHANGE_NAME = "RpiTopics";
            Publisher publisher = new Publisher();

            if(Objects.nonNull(idRpi)) {                                                                              // Avoids filling DB with values when sensor if off
                publisher.publish(EXCHANGE_NAME,"User.RPi.Room."+idRpi,request);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args)  throws Exception{

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
                //Voir avec MAlick


            }
            else{
                //sauvegarder dans bdd

                try {
                    DataService dataService= new DataService();

                    System.out.println(" TOUT EST BIEN RECU");
                    //DataAPI.saveData(new Data().jsonstringToData(response));
                    Data data = new Data().jsonstringToData(response);

                    dataService.saveData(data);

                    System.out.println(" TOUT EST BIEN SAUVEGARDE");
                }
                 catch (Exception ex){
                    System.out.println(ex);
                }
            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}

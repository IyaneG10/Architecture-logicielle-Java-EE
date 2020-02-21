package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.Publisher;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java .sql.*;


@SpringBootApplication
public class RabbitMQService {

    @Autowired
   private static DataService dataService;

    public static void main(String[] args)  throws Exception{

        System.out.println(" START !\n To stop, type 'exit'");

        final String EXCHANGE_NAME = "RPiTopics";
        final String hostIP = "193.48.57.166";
        final String username = "ima2a5-4fun";
        final String password = "glopglop";
        MariaDBService mariaDBService = new MariaDBService("jdbc:mariadb://localhost:3306/sysco", "admin", "admin");


        ConnectionFactory factory = new ConnectionFactory();

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

            String jsonStringData = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + jsonStringData + "'");


            if(delivery.getEnvelope().getRoutingKey().contains("Rpi.User.Room.")){
                //se connecter au controller
                //Voir avec MAlick
            }
            else{
                try {

                 //   DataService dataService= new DataService();
                    Data data= new Data(jsonStringData);
                    System.out.println(data);
                    //dataService.saveData(data);

                    mariaDBService.addData(data);
                    //DataAPI.saveData(new Data(jsonStringData));
                    //Data data = new Data(jsonStringData);

                    //dataService.saveData(data);''''''''''''''''''''''''''''''''''y
                }

                 catch (Exception ex){

                    System.out.println(ex);
                }

            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }


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
}

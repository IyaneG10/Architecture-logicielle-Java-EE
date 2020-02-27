package com.al.project.SysCo;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Service.MariaDBService;
import com.al.project.SysCo.Service.Publisher;
import com.al.project.SysCo.Service.DataService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;


@SpringBootApplication
public class RabbitMQService {

    @Autowired
   private static DataService dataService;
    private HashMap<String, String> capitalCities = new HashMap<String, String>();
    private HashMap<String, String> capitalCities = new HashMap<String, String>();


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
            System.out.println(" [i] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + jsonStringData + "'");


            if(delivery.getEnvelope().getRoutingKey().contains("Rpi.User.Room.")){
                //se connecter au controller
                //Voir avec MAlick
            }
            else{
                try {

                    Data data= new Data(jsonStringData);

                    System.out.println(" [+] Data saved in database...\n");
                    mariaDBService.addData(data);
                }

                 catch (Exception ex){

                    System.out.println(ex);
                }
            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }


    public static String GetRealTimeTopics(String idRpi, String request){

        try {
            String EXCHANGE_NAME = "RpiTopics";

            Publisher publisher = new Publisher();

            if(Objects.nonNull(idRpi)) {                                                                              // Avoids filling DB with values when sensor if off
                publisher.publish(EXCHANGE_NAME,"User.RPi.Room."+idRpi,request);
            }

            return "";
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

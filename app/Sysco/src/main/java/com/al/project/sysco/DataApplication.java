package com.al.project.sysco;

import com.al.project.sysco.Model.Data;
import com.al.project.sysco.Service.DataService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;


@SpringBootApplication
public class DataApplication {

    @Autowired
    private static DataService dataService;

    public static void main(String[] args)  throws Exception{

        System.out.println(" START !\n To exit : 'Ctrl-C'");

        final String EXCHANGE_NAME = "RPiTopics";
        final String hostIP = "localhost";
        final String username = "pifou";
        final String password = "pasglop";

        DataService dataService = new DataService("jdbc:mariadb://localhost:3306/sysco", "admin", "admin");

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

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String jsonStringData = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [i] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + jsonStringData + "'");


            if(delivery.getEnvelope().getRoutingKey().contains("Rpi.User.Room.")){

            }
            else{
                try {

                    Data data= new Data(jsonStringData);

                    System.out.println(" [+] Data saved in database...\n");
                    dataService.addData(data);
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
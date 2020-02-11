package com.al.project.SysCo.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import static java.lang.System.*;

public class Publisher {

    //private static String EXCHANGE_NAME;
    private final String hostIP = "193.48.57.166";
    private final String username = "ima2a5-4fun";
    private final String password = "glopglop";


    public void publish(String EXCHANGE_NAME, String routingKey, String message) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);
        //EXCHANGE_NAME = EXCHANGE_NAME;

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
            //out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }
}
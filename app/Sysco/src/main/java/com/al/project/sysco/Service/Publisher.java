package com.al.project.sysco.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Publisher {

    private final String hostIP = "localhost";
    private final String username = "pifou";
    private final String password = "pasglop";


    public void publish(String EXCHANGE_NAME, String routingKey, String message) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
package com.al.project.SysCo.RPi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import static java.lang.System.*;

public class Publisher {

    private static String EXCHANGE_NAME;
    private final String hostIP;

    public Publisher(){

        hostIP = "localhost";
    }


    public void publish(String topicName, String routingKey, String message) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        EXCHANGE_NAME = topicName;

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
            out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }
}
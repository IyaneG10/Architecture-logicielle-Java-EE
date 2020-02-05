package com.al.project.SysCo.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class Subscriber {


    //private static final String EXCHANGE_NAME = "topic_logs";

    private static String EXCHANGE_NAME;
    private final String hostIP = "193.48.57.166";
    private final String username = "ima2a5-4fun";
    private final String password = "glopglop";

    public static void main(String[] argv) throws Exception {
        /*ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        if (argv.length < 1) {
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            System.exit(1);
        }

        for (String bindingKey : argv) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

        */

    }

    public  void subscribe(String topicName, String routingKey) throws Exception{

        EXCHANGE_NAME = routingKey;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);


        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();

            if (Objects.isNull(topicName) || Objects.isNull(routingKey)) {
                System.err.println("Bad usage subscribe method");
                System.exit(1);
            }

            //for (String bindingKey : argv) {
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
            //}

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
    }
}
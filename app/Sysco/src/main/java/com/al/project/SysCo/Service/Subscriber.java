package com.al.project.SysCo.Service;

import com.al.project.SysCo.DataApplication;
import com.al.project.SysCo.RPi.Rpi;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class Subscriber {

    private static final String EXCHANGE_NAME = "RPiTopics";

    //private static String EXCHANGE_NAME;
    private final String hostIP = "193.48.57.166";
    private final String username = "ima2a5-4fun";
    private final String password = "glopglop";
    private Object obj;

    public Subscriber(Object obj) {

        this.obj = obj;
    }

    public void Subscribe(String routingKey) throws Exception{

        //EXCHANGE_NAME = routingKey;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();

            /*if (Objects.isNull(routingKey)) {
                System.err.println("Bad usage subscribe method");
                System.exit(1);
            }*/

            //for (String bindingKey : argv) {
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
            //}

            System.out.println(" [*] Waiting for messages. To exit type 'exit'");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {

                String response = new String(delivery.getBody(), StandardCharsets.UTF_8);
                if(obj.getClass().toString().equals("Rpi")){
                    Rpi rpi = (Rpi)obj;
                    rpi.sendTopicResponseToRabbit(response);
                }
                else{

                    DataService dataService = (DataService)obj;
                    dataService.sendTopicResponse(delivery.getEnvelope().getRoutingKey(), response);
                }
                System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + response + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
    }
}
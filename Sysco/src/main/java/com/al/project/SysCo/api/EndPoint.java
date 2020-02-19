package com.al.project.SysCo.api;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Represents a connection with a queue
 * @author syntx
 *
 */
public abstract class EndPoint{


    private final String hostIP = "193.48.57.166";
    private final String username = "ima2a5-4fun";
    private final String password = "glopglop";
    final String EXCHANGE_NAME = "RPiTopics";

    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endpointName) throws IOException, TimeoutException {


        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of rabbitmq server
        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);



        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(endpointName, false, false, false, null);
    }


    /**
     * Close channel and connection. Not necessary as it happens implicitly any way.
     * @throws IOException
     */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
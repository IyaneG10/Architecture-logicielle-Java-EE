package com.al.project.SysCo.api;


//import org.apache.commons.lang.SerializationUtils;

import org.springframework.amqp.utils.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;


/**
 * The producer endpoint that writes to the queue.
 * @author syntx
 *
 */
public class Producer extends EndPoint{

    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
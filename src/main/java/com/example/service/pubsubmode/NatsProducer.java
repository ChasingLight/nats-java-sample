package com.example.service.pubsubmode;

import io.nats.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class NatsProducer {

    @Autowired
    private Connection natsConnection;

    public void sendMessage(String subject, String message) {
        try {
            natsConnection.publish(subject, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end method
}

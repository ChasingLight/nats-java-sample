package com.example.service.pubsubmode;

import com.example.nats.enums.NatsSubject;
import com.example.nats.subscribe.ISubscribe;
import io.nats.client.Connection;
import io.nats.client.Message;
import org.springframework.stereotype.Service;

@Service
public class NatsConsumer2 implements ISubscribe {

    @Override
    public String getSubject() {
        return NatsSubject.PUB_SUB.getSubject();
    }

    @Override
    public String getQueueGroup() {
        return null;
    }

    @Override
    public void consume(Message message, Connection conn) {
        String msgInfo = new String(message.getData());
        System.out.println("Consumer2 Received message: " + msgInfo);
    }
}

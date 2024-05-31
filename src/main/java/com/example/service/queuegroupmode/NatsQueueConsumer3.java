package com.example.service.queuegroupmode;

import com.example.nats.enums.NatsQueueGroup;
import com.example.nats.enums.NatsSubject;
import com.example.nats.subscribe.ISubscribe;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class NatsQueueConsumer3 implements ISubscribe {

    @Override
    public String getSubject() {
        return NatsSubject.QUEUE_GROUP.getSubject();
    }

    @Override
    public String getQueueGroup() {
        return NatsQueueGroup.DC_TEST.getQueueGroup();
    }

    @Override
    public void consume(Message message, Connection conn) {
        String msgInfo = new String(message.getData());
        System.out.println("Consumer3 Received message: " + msgInfo);
    }
}

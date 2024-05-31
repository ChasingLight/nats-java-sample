package com.example.service.reqreplymode;

import com.example.nats.enums.NatsQueueGroup;
import com.example.nats.enums.NatsSubject;
import com.example.nats.subscribe.ISubscribe;
import com.example.util.WeatherUtil;
import io.nats.client.Connection;
import io.nats.client.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class NatsReplyConsumer2 implements ISubscribe{

    @Autowired
    private WeatherUtil weatherUtil;

    @Override
    public String getSubject() {
        return NatsSubject.REQ_REPLY.getSubject();
    }

    @Override
    public String getQueueGroup() {
        return NatsQueueGroup.DC_TEST.getQueueGroup();
    }

    @Override
    public void consume(Message msg, Connection connection) {
        String cityName = new String(msg.getData());
        // 处理并响应
        System.out.println("Consumer2 Received message: " + cityName);
        String weatherInfo = weatherUtil.getWeatherInfoByCityName(cityName);
        System.out.println("Consumer2 resp info: " + weatherInfo);
        connection.publish(msg.getReplyTo(), weatherInfo.getBytes(StandardCharsets.UTF_8));
    }
}

package com.example.service.reqreplymode;

import io.nats.client.Connection;
import io.nats.client.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class NatsReplyProducer {

    @Autowired
    private Connection natsConnection;

    public String sendMessage(String subject, String cityName) {
        try {
            Message respMsg = natsConnection.request(subject, cityName.getBytes(StandardCharsets.UTF_8), Duration.ofSeconds(30));
            return new String(respMsg.getData());
        }catch (InterruptedException e){
            e.printStackTrace();
            return "请求超时：超过了30秒";
        }catch (Exception e){
            e.printStackTrace();
            return "代码异常";
        }
    }//end method
}

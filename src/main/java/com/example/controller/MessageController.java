package com.example.controller;

import com.example.nats.enums.NatsSubject;
import com.example.service.pubsubmode.NatsProducer;
import com.example.service.queuegroupmode.NatsQueueProducer;
import com.example.service.reqreplymode.NatsReplyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinhaodong
 * @date 2024/5/30 15:53
 */


@RestController
public class MessageController {

    @Autowired
    private NatsProducer natsProducer;

    @Autowired
    private NatsQueueProducer queueProducer;

    @Autowired
    private NatsReplyProducer replyProducer;

    /**
     * 发布-订阅模式
     * 说明：订阅 subject的所有订阅者都能接收到消息，属于消息广播。
     * @param message
     * @return
     */
    @GetMapping("/pubSubSend")
    public String sendMessage(@RequestParam String message) {
        natsProducer.sendMessage(NatsSubject.PUB_SUB.getSubject(), message);
        return "Message sent: " + message;
    }

    /**
     * 队列组 模式
     * 说明：同一组里只有一个 subscriber 会收到此消息，实现负载均衡。
     * @param message
     * @return
     */
    @GetMapping("/queueGroupSend")
    public String sendQueueMessage(@RequestParam String message) {
        queueProducer.sendMessage(NatsSubject.QUEUE_GROUP.getSubject(), message);
        return "Message sent: " + message;
    }

    @GetMapping("/replySend")
    public String sendReplyMessage(@RequestParam String message) {
        String respWeatherInfo = replyProducer.sendMessage(NatsSubject.REQ_REPLY.getSubject(), message);
        return "Message resp: " + respWeatherInfo;
    }
}

package com.example.nats.subscribe;

import io.nats.client.Connection;
import io.nats.client.Message;

/**
 * 订阅接口
 * 说明：消息-消费者均实现这个接口
 * @author jinhaodong
 * @date 2024/5/31 13:56
 */
public interface ISubscribe {

    /**
     * 订阅的主题
     * @return
     */
    String getSubject();

    String getQueueGroup();

    /**
     * 消费消息
     * @param msg
     */
    void consume(Message msg, Connection connection);
}

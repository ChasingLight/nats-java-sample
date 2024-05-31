package com.example.nats.enums;

/**
 * Nats消息主题-枚举类
 * @author jinhaodong
 * @date 2024/5/31 14:04
 */
public enum NatsSubject {

    PUB_SUB("pubSub", "发布-订阅模式"),
    QUEUE_GROUP("queueGroup", "队列组模式"),
    REQ_REPLY("reqReply", "请求-响应模式");

    private final String subject;
    private final String description;

    NatsSubject(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }
}

package com.example.nats.enums;

/**
 * Nats消息主题-枚举类
 * @author jinhaodong
 * @date 2024/5/31 14:04
 */
public enum NatsQueueGroup {

    DC_TEST("dcTest", "开发环境-队列组");

    private final String queueGroup;
    private final String description;

    NatsQueueGroup(String queueGroup, String description) {
        this.queueGroup = queueGroup;
        this.description = description;
    }

    public String getQueueGroup() {
        return queueGroup;
    }

    public String getDescription() {
        return description;
    }
}

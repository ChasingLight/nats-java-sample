package com.example.nats.config;

import com.example.nats.subscribe.ISubscribe;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import io.nats.client.Options;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.nats.listener.NatsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

/**
 * @author jinhaodong
 * @date 2024/5/30 15:49
 */
@Configuration
public class NatsConfig {

    @Value("${nats.server}")
    private String natsServer;

    @Value("${nats.maxReconnect}")
    private int maxReconnect;

    @Value("${nats.reconnectWait}")
    private int reconnectWait;

    @Value("${nats.connectionTimeout}")
    private int connectionTimeout;


    /**
     * 连接 NATS Server
     * 1> 读取配置文件，建立连接；
     * 2> 监听'连接成功'事件，创建分发器---消费者-订阅-主题。
     * @return
     */
    @Bean
    @SneakyThrows
    public Connection natsConnection(List<ISubscribe> subscribeList) {
        String[] serverList = natsServer.split(",");
        Options.Builder builder = new Options.Builder()
                // 配置 nats 服务器地址
                .servers(serverList)
                // 最大重连次数
                .maxReconnects(maxReconnect)
                // 重连等待时间
                .reconnectWait(Duration.ofSeconds(reconnectWait))
                // 连接超时时间
                .connectionTimeout(Duration.ofSeconds(connectionTimeout))
                // 监听连接事件
                .connectionListener(new NatsListener(subscribeList));
        return Nats.connect(builder.build());
    }
}

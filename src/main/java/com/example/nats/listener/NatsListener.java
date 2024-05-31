package com.example.nats.listener;

import com.example.nats.subscribe.ISubscribe;
import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import io.nats.client.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinhaodong
 * @date 2024/5/31 11:23
 */
@Slf4j
public class NatsListener implements ConnectionListener {

    public List<ISubscribe> subscribeList;

    public List<Dispatcher> dispatcherList = new ArrayList<>();

    public NatsListener(List<ISubscribe> subscribeList){
        this.subscribeList = subscribeList;
    }

    @Override
    public void connectionEvent(Connection conn, Events type) {
        switch (type){
            case CLOSED:
                log.error("nats-server未启动");
                break;
            case CONNECTED:
                log.info("与nats-server连接成功了！");
                createDispatcher(conn);
                break;
            case DISCONNECTED:
                log.error("与nats-server断开连接了！");
                break;
            case RECONNECTED:
                log.error("与nats-server重连成功了！");
                break;
        }
    }//end method


    /**
     * 连接成功nats-server后，为消息消费者创建分发器
     */
    public void createDispatcher(Connection conn){
        log.info("开始创建消息-分发器");
        try{
            subscribeList.forEach(subscribe -> {
                // 创建-分发器
                Dispatcher dispatcher;
                if (StringUtils.hasText(subscribe.getQueueGroup())){
                    // 队列组模式（负载均衡）
                    dispatcher = conn.
                            createDispatcher((m) -> subscribe.consume(m, conn))
                            .subscribe(subscribe.getSubject(), subscribe.getQueueGroup());
                }else{
                    // 发布-订阅模式（广播）
                    dispatcher = conn.
                            createDispatcher((m) -> subscribe.consume(m, conn))
                            .subscribe(subscribe.getSubject());
                }
                // 分发器列表
                dispatcherList.add(dispatcher);
            });
        }catch (Exception e){
            e.printStackTrace();
            log.error("创建消息-分发器，代码异常[{}]", e.getMessage());
        }

    }//end method

}

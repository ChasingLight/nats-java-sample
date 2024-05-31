## NATS + SpringBoot 样例

1. 安装NATS Server

   window操作系统-下载地址：https://github.com/nats-io/nats-server/releases/download/v2.9.21/nats-server-v2.9.21-windows-amd64.zip

   解压完成后，文件夹中有一个`nats-server.exe`文件，双击执行就行。

   默认端口为4222

2. 代码加载启动

    1.  NatsConfig---读取配置信息，创建NATS连接（可设置重连次数、连接超时时间等）；

    2.  NatsListener---监听连接NATS Server事件，为每个消息-消费者 创建 Dispatcher(消息分发器)；

    3.  NatsSubject---记录所有的NATS主题、NatsQueueGroup---记录NATS 队列组。

    4. MessageController---三种模式代码示例。

       三种模式额外说明：

       **1> 发布-订阅模式（Publish-Subscribe）**

       > 当 publisher 往 subject 上发布一条消息后，此 subject 上所有 subscriber 都能收到 此消息，属于一种广播。

       **2> 队列组模式（Queue Groups）**

       > 把 subscriber 分组，那么当 publisher 往 subject 上发布一条消息后，同一组里只有一个 subscriber 会收到此消息，从而实现了负载均衡（loading balance）。
       >
       > ---云打印平台作为NATS 订阅者，集群部署，就使用了此种模式，保证只有一个subscriber接收处理消息！！！

       **3> 请求-响应模式（Request-Reply）**

       【意义：可用于rpc调用、消息ack（消费者告知发送者-消息接收到了）】

       > 一般来说，消息系统是以异步的形式工作，也就是说，publisher 往 subject 上发布一条消息后，并不在意 subscriber 的 reply 是什么。
       >
       > 如果 publisher 在意 subscriber 的 reply 是什么的话，那么消息系统就应该以同步的形式工作。
       >
       > 在具体实现中，是通过两次发布订阅来完成的：当 publisher 发布消息后，它会订阅一个特定的 subject，当 subscriber 处理完消息后，它会把 reply 发布到这个特定的 subject。当然，整个过程对使用者是透明的。

3. 启动演示

    1. 发布-订阅模式：http://localhost:8080/pubSubSend?message=JadenOliver
    2. 队列组模式：http://localhost:8080/queueGroupSend?message=JadenOliver
    3. 请求-响应模式：http://localhost:8080/replySend?message=JadenOliver
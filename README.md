# kafka快速使用

### 极少的依赖及配置信息，实现快速使用kafka进行消息发送及消费

配置明细
```yaml
spring:
  kafka:
    producer:
      bootstrap-servers: 127.0.0.1:9092                   #集群以英文逗号分割
      batch-size: 131072                                  #一次最多发送数据量
      retries: 1                                          #发送失败后的重复发送次数
      buffer-memory: 67108864                             #32M批处理缓冲区
      linger: 100                                         #如果不设置linger.ms，其默认值就是0，也就说即使batch不满也会发送出去。可现在设置了linger.ms，这样这些本该早就发出去的消息被迫至少等待了linger.ms时间，所以说增加了发送方的延迟
      #push模块可以适当延迟
    consumer:
      bootstrap-servers: 127.0.0.1:9092               #集群以英文逗号分割
      auto-offset-reset: latest                           #无提交的offset时，消费新产生的该分区下的数据
      max-poll-records: 500                               #批量消费一次最大拉取的数据量
      enable-auto-commit: false                           #是否开启自动提交
      auto-commit-interval: 1000                          #自动提交的间隔时间
      session-timeout: 20000                              #连接超时时间
      max-poll-interval: 30000                            #手动提交设置与poll的心跳数,如果消息队列中没有消息，等待毫秒后，调用poll()方法。如果队列中有消息，立即消费消息，每次消费的消息的多少可以通过max.poll.records配置。
      max-partition-fetch-bytes: 15728640                 #设置拉取数据的大小,15M
      heartbeat-interval: 3000                            #心跳间隔
      fetch-min-size: 1                                   #每次fetch请求时，server应该返回的最小字节数
      fetch-max-wait: 500                                 #Fetch请求发给broker后，在broker中可能会被阻塞的（当topic中records的总size小于fetch.min.bytes时），此时这个fetch请求耗时就会比较长。这个配置就是来配置consumer最多等待response多久
    listener:
      batch-listener: true                                #是否开启批量消费，true表示批量消费
      poll-timeout: 1000
      concurrency: 3

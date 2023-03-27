package com.bmdst.services.starter.kafka.service.impl;

import com.bmdst.services.starter.kafka.service.IKafkaService;
import com.bmdst.services.starter.kafka.domain.KafkaContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author Kang Weibing
 * @description Kafka 服务，主要用于发送消息
 * @since 2021-04-26 19:44
 */

@Slf4j
public class KafkaService implements IKafkaService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Async
    public void send(String topic, String message) {
        try {
            KafkaContext kafkaContext = new KafkaContext(topic, message);
            send(kafkaContext);
        } catch (Exception e) {
            log.error("topic {} -- message {}", topic, message);
            log.error("消息发送异常", e);
        }
    }

    @Override
    @Async
    public void send(KafkaContext kafkaContext) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaContext.getTopic(),
                kafkaContext.getPartition(),
                kafkaContext.getTimestamp(),
                kafkaContext.getKey(),
                kafkaContext.getMessage());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(final SendResult<String, String> result) {
                RecordMetadata metaData = result.getRecordMetadata();
                log.debug("topic {}, partition: {}, offset: {}  value: {}",
                        metaData.topic(), metaData.partition(), metaData.offset(), kafkaContext.getMessage());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                log.error("发送消息失败: {} ", kafkaContext.getMessage(), throwable);
            }
        });
    }
}

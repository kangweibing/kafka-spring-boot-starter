package com.bmdst.services.starter.kafka.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Kang Weibing
 * @description Kafka Context
 * @since 2021-04-26 19:44
 */

@Setter
@Getter
@NoArgsConstructor
public class KafkaContext {

    Long timestamp;
    private String topic;
    private String key;
    private String message;
    private Integer partition;

    public KafkaContext(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }
}

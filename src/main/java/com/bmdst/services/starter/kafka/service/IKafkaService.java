package com.bmdst.services.starter.kafka.service;


import com.bmdst.services.starter.kafka.domain.KafkaContext;

public interface IKafkaService {

    void send(String topic, String message);

    void send(KafkaContext kafkaContext);
}

package com.bmdst.services.starter.kafka.config;

import com.bmdst.services.starter.kafka.service.impl.KafkaService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kang Weibing
 * @description Kafka spring boot 自动装配文件
 * @since 2021-06-22 19:44
 */

@ConditionalOnClass(KafkaService.class)
@Configuration
public class KafkaAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(KafkaService.class)
    public KafkaService kafkaService() {
        return new KafkaService();
    }
}
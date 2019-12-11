package com.monster.melon.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerConfig {

    @Bean
    public Queue workerQueue(){
        return new Queue("mq.worker");
    }


}

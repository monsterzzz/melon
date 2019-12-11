package com.monster.melon.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class DeadMessage {

    @Bean
    public Queue queue(){
        return new Queue("mq.DQueue",true);
    }

    @Bean
    public CustomExchange customExchange(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return new CustomExchange("ex.delay","x-delayed-message",true,false,map);
    }

    @Bean
    public Binding bindingQueue2Exchange(){
        log.info("binding...");
        return BindingBuilder.bind(queue()).to(customExchange()).with("mq.DQueue").noargs();
    }

}

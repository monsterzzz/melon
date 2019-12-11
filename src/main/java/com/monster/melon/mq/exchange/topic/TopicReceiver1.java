package com.monster.melon.mq.exchange.topic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "mq.test5")
public class TopicReceiver1 {

    @RabbitHandler
    public void process(String msg){
        log.info("test5: " + msg);
    }

}

package com.monster.melon.mq.exchange.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "mq.test4")
public class Receiver4 {

    @RabbitHandler
    public void process(String msg){
        log.info(msg);
    }

}

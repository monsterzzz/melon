package com.monster.melon.mq.exchange.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "mq.test1")
public class Receiver1 {

    @RabbitHandler
    public void process(String msg){
        log.info(msg);
    }

}

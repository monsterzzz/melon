package com.monster.melon.mq.exchange.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender2 {

    private final RabbitTemplate rabbitTemplate;

    public Sender2(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String msg){
        rabbitTemplate.convertAndSend("ex.direct1","r.monster.d1","hello direct1: "+msg);
        rabbitTemplate.convertAndSend("ex.direct1","r.monster.d2","hello direct2: "+msg);

    }

}

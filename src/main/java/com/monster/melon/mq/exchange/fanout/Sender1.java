package com.monster.melon.mq.exchange.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender1 {

    private final RabbitTemplate template;

    public Sender1(RabbitTemplate template) {
        this.template = template;
    }

    public void send(String msg){
        template.convertAndSend("ex.fanout1","","hello fanout," + msg);
    }
}

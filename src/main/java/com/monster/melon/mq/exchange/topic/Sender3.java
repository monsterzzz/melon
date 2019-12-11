package com.monster.melon.mq.exchange.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender3 {

    private final RabbitTemplate template;


    public Sender3(RabbitTemplate template) {
        this.template = template;
    }

    public void send(String msg){
        template.convertAndSend("ex.topic","com.monster.error",msg);
        template.convertAndSend("ex.topic","r.monster.melon",msg);
        template.convertAndSend("ex.topic","r.monster.gVideo",msg);
        template.convertAndSend("ex.topic","com.system.error",msg);
        template.convertAndSend("ex.topic","r.monster.error.tmp.test",msg);
    }

}


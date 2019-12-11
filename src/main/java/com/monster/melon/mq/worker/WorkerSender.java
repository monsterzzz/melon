package com.monster.melon.mq.worker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerSender {

    private final RabbitTemplate template;

    public WorkerSender(RabbitTemplate template) {
        this.template = template;
    }

    public void send(String msg){
        template.convertAndSend("mq.worker",msg);
    }
}

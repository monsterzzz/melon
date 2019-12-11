package com.monster.melon.mq.exchange.dead;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class DeadSender {

    private final RabbitTemplate template;

    public DeadSender(RabbitTemplate template) {
        this.template = template;
    }

    public void send(String msg){

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = f.format(new Date());
        log.info("发送时间: " + now);
        template.convertAndSend("ex.delay", "mq.DQueue", msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay",3000);
                return message;
            }
        });
    }


}

package com.monster.melon.mq.hello;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Slf4j
public class HelloSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final RabbitTemplate rabbitTemplate;
    private final static String QUEUE_NAME = "hello";

    public HelloSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    public void send(String msg){
        String context = "hello " + msg;
        rabbitTemplate.convertAndSend(QUEUE_NAME,context);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        // 投递到rabbitmq是否成功
        String flag = b ? "成功" : "失败";
        log.info(" 投递到rabbitmq: " + flag + "，消息: ");
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("returnMessage");
        log.info(message.toString());
        log.info("reply code : " + i);
        log.info("exchange : " + s1);
        log.info("routingKey : " + s2);
    }
}

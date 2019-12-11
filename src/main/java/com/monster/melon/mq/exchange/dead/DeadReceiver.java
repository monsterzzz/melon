package com.monster.melon.mq.exchange.dead;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
@RabbitListener(queues = "mq.DQueue")
public class DeadReceiver {

    @RabbitHandler
    public void process(Message message){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(f.format(new Date()) + " receive: " + Arrays.toString(message.getBody()));
    }

    @RabbitHandler
    public void process(String msg){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(f.format(new Date()) + " receive: " + msg);
    }

}

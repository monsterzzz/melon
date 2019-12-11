package com.monster.melon.mq.hello;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Component
@Slf4j
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(
                        value = "hello"
                ),
                exchange = @Exchange(
                        value = "direct"
                )
        ),
        concurrency = "1",
        ackMode = "MANUAL"
)
public class HelloReceiver {

    private final static String QUEUE_NAME = "hello";

    @RabbitHandler
    public void process(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InterruptedException, IOException {
        log.info(channel.toString());
        log.info(Long.toString(tag));
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm-ss").format(new Date()) + "  helloQueue : " + msg);
        channel.basicAck(tag,false); // multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。
        // channel.basicNack(tag,false,true);
        Thread.sleep(1000);
    }

}

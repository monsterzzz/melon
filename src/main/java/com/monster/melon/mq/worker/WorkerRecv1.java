package com.monster.melon.mq.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "mq.worker")
public class WorkerRecv1 {

    private Integer count = 0;

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
        count++;
        log.info(msg + " " + count.toString());
        Thread.sleep(2000);
    }

}

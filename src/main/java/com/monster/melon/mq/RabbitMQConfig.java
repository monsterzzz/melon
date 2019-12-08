package com.monster.melon.mq;
import com.rabbitmq.client.impl.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
    我要发送的消息是什么
    我应该需要创建什么样的消息模型：DirectExchange+RoutingKey？TopicExchange+RoutingKey？等
    我要处理的消息是实时的还是需要延时/延迟的？
    消息的生产者需要在哪里写，消息的监听消费者需要在哪里写，各自的处理逻辑是啥
 */
@Configuration
@Slf4j
public class RabbitMQConfig {

    private final CachingConnectionFactory cachingConnectionFactory;
    private final static String queueName = "helloMQ";

    public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public Queue helloQueue(){
        return new Queue(queueName);
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }

    @Bean
    public Queue dirQueue(){
        return new Queue("direct");
    }


}

package com.monster.melon.mq.config;
import com.rabbitmq.client.impl.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory){
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setConfirmCallback(new RabbitConfirmCallback);
//    }


    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean("mq.test1")
    public Queue testQueue1(){
        return new Queue("mq.test1");
    }

    @Bean("mq.test2")
    public Queue testQueue2(){
        return new Queue("mq.test2");
    }

    @Bean("mq.test3")
    public Queue testQueue3(){
        return new Queue("mq.test3");
    }

    @Bean("mq.test4")
    public Queue testQueue4(){
        return new Queue("mq.test4");
    }

    @Bean("mq.test5")
    public Queue testQueue5(){
        return new Queue("mq.test5");
    }

    @Bean("mq.test6")
    public Queue testQueue6(){
        return new Queue("mq.test6");
    }

    @Bean("mq.test7")
    public Queue testQueue7(){
        return new Queue("mq.test7");
    }

    @Bean("mq.test8")
    public Queue testQueue8(){
        return new Queue("mq.test8");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("ex.fanout1");
    }

    @Bean
    public Binding bindingQueue2Exchange1(){
        return BindingBuilder.bind(testQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingQueue2Exchange2(){
        return BindingBuilder.bind(testQueue2()).to(fanoutExchange());
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("ex.direct1");
    }

    @Bean
    public Binding bindingQueue2Exchange3(){
        return BindingBuilder.bind(testQueue3()).to(directExchange()).with("r.monster.d1");
    }

    @Bean
    public Binding bindingQueue2Exchange4(){
        return BindingBuilder.bind(testQueue3()).to(directExchange()).with("r.monster.d2");
    }

    @Bean
    public Binding bindingQueue2Exchange5(){
        return BindingBuilder.bind(testQueue4()).to(directExchange()).with("r.monster.d1");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("ex.topic");
    }

    @Bean
    public Binding bindingQueue2Exchange6(){
        return BindingBuilder.bind(testQueue5()).to(topicExchange()).with("*.*.error");
    }

    @Bean
    public Binding bindingQueue2Exchange7(){
        return BindingBuilder.bind(testQueue5()).to(topicExchange()).with("r.monster.#");
    }

    @Bean Binding bindingQueue2Exchange8(){
        return BindingBuilder.bind(testQueue6()).to(topicExchange()).with("#");
    }



}

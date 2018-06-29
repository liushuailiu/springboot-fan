package com.example.springboot_day01.system.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author fly
 */
@Component

public class RabbitMqConfig {

    @RabbitListener(queues = "spring.rabbitmq.queue")
    public void queueListener(String message){
        System.out.println(message+"----------------------");
    }

}

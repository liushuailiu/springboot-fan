package com.example.springboot_day01.system.rabbit;

import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.view.WebSocket;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fly
 * 消费者
 */

@Component
public class CustomerConfig {

    /**
     * 监听名为  spring.rabbitmq.queue 的队列
     * @param student 根据传入正文设置类型
     */
    @RabbitListener(queues = "spring.rabbitmq.queue")
    public void customer(Student student){
        for (WebSocket w:WebSocket.webSockets) {
            try {
                w.sendMessage(student.toString());
            } catch (IOException e) {
                continue;
            }
        }
    }
}

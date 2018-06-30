package com.example.springboot_day01.view;

import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.service.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/set")
    public Object set(){

        Student student = studentService.queryStudent();
        /**
         * 调用生产者
         * 1.交换机地址
         * 2.路由地址
         * 3.消息正文（任意对象)
         */
        rabbitTemplate.convertAndSend("spring.rabbitmq.exchange","spring.rabbitmq.queue",student);
        return "success";
    }

    @GetMapping("/get")
    public Object get(){
        return studentService.querySystemRole();
    }

}


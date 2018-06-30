package com.example.springboot_day01.service.impl;

import com.example.springboot_day01.mapper.mysql.SystemRoleMapper;
import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.mapper.oracle.StudentMapper;
import com.example.springboot_day01.pojo.SystemRole;
import com.example.springboot_day01.service.StudentService;
import com.example.springboot_day01.system.listener.StudentLoginEvent;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 *
 *  CacheConfig(cacheNames = "com.example.springboot_day01.service.impl")
 */
@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SystemRoleMapper systemRoleMapper;

    /**
     * 事件的发布者,springboot内置
     */
    @Autowired
    ApplicationContext applicationContext;

    @Override

    /**
     *  取消原来的redis缓存实现方式
     *  Cacheable(key = "'queryStudent'")
     */
    public Student queryStudent() {
        return studentMapper.queryAllStudent();
    }

    @Override
    /**
     * Cacheable(key = "'queryStudent'")
     */
    public SystemRole querySystemRole(){
        return systemRoleMapper.selectByPrimaryKey(4);
    }

    @Override
    public Student selectStudent() {
        return null;
    }

    /**
     * 该方法用来模拟用户登录,
     * 在用户登录之后,可以有选择的把一些业务交给监听器处理,
     * 完成业务的解耦,让这个业务系统不会变的特别繁琐,繁杂,难以维护,更好的解耦,
     * 所以,在这里用户登录之后,完成事件的发布
     * @param id
     * @return
     */
    @Override
    public Student queryStudentById(Integer id) {
        Student student = studentMapper.selectStudentById(id);
        /**
         * 如果student!=null,说明用户已经
         * 成功登录,那么我们在这里发布时间
         */
        if(student!=null){
            applicationContext.publishEvent(new StudentLoginEvent(this,student));
        }
        return student;
    }


}

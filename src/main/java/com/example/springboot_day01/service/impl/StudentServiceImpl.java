package com.example.springboot_day01.service.impl;

import com.example.springboot_day01.mapper.mysql.SystemRoleMapper;
import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.mapper.oracle.StudentMapper;
import com.example.springboot_day01.pojo.SystemRole;
import com.example.springboot_day01.service.StudentService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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



}

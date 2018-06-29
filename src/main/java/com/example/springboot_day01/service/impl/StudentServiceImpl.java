package com.example.springboot_day01.service.impl;

import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.mapper.StudentMapper;
import com.example.springboot_day01.service.StudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
@Service
@CacheConfig(cacheNames = "com.example.springboot_day01.service.impl")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    @Cacheable(key = "'user'.concat(#a0)")
    public List<Student> queryStudent(String name) {
        return studentMapper.queryAllStudent(name);
    }

    @Override
    @Cacheable(key = "'selectById'.concat(#a0)")
    public Student selectById(Serializable id) {
        return super.selectById(id);
    }

}

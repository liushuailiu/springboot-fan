package com.example.springboot_day01.service.impl;

import com.example.springboot_day01.mapper.mysql.SystemRoleMapper;
import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.mapper.oracle.StudentMapper;
import com.example.springboot_day01.pojo.SystemRole;
import com.example.springboot_day01.service.StudentService;
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
 */
@Service
@CacheConfig(cacheNames = "com.example.springboot_day01.service.impl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SystemRoleMapper systemRoleMapper;

    @Override
    @Cacheable(key = "'queryStudent'.concat(#a0)")
    public List<Student> queryStudent(String name) {
        return studentMapper.queryAllStudent(name);
    }
    @Override
    @Cacheable(key = "'systemRoleMapper'")
    public SystemRole querySystemRole(){
        return systemRoleMapper.selectByPrimaryKey(4);
    }

}

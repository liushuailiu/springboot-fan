package com.example.springboot_day01.service;

import com.example.springboot_day01.pojo.Student;
import com.example.springboot_day01.pojo.SystemRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
public interface StudentService {

    Student queryStudent();
    SystemRole querySystemRole();
    Student selectStudent();
}

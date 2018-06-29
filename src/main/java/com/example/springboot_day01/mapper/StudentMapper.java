package com.example.springboot_day01.mapper;

import com.example.springboot_day01.pojo.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    public List<Student> queryAllStudent(String name);

}

package com.example.springboot_day01.mapper.oracle;

import com.example.springboot_day01.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
@Mapper
public interface StudentMapper {


    /**
     * 查询所有学生
     * @return
     */
    public Student queryAllStudent();

    /**
     * 模拟用户登录
     * @param id
     * @return
     */
    Student selectStudentById(Integer id);
}

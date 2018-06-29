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


    public Student queryAllStudent();

}

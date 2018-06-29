package com.example.springboot_day01.mapper.mysql;

import com.example.springboot_day01.pojo.SystemRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SystemRoleMapper {

    SystemRole selectByPrimaryKey(Integer roleid);

}
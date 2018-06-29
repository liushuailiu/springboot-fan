package com.example.springboot_day01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author 17799
 */
@SpringBootApplication
@MapperScan("com.example.springboot_day01.mapper")
@EnableCaching
public class SpringbootDay01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDay01Application.class, args);
    }
}

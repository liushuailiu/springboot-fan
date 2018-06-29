package com.example.springboot_day01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author 17799
 */
@SpringBootApplication
@EnableCaching
public class SpringbootDay01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDay01Application.class, args);
    }
}

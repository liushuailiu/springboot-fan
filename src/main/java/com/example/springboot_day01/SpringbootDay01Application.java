package com.example.springboot_day01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 17799
 */
@SpringBootApplication
// 开启缓存
//@EnableCaching
// 任务调度
@EnableScheduling
public class SpringbootDay01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDay01Application.class, args);
    }
}

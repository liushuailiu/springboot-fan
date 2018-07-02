package com.example.springboot_day01.system.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author fly
 */
@Repository
public class RedisMapper {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public  void setValue(String key,String value){
        this.stringRedisTemplate.opsForValue().set(key,value,4, TimeUnit.HOURS);
    }

    public String getValue(String key){
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteValueByKey(String key){
        stringRedisTemplate.delete(key);
    }

}

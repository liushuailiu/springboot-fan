package com.example.springboot_day01.system.redis;

/**
 * @author fly
 */
public @interface CacheConfig {

    String[] methods() default {"select","query"};

}

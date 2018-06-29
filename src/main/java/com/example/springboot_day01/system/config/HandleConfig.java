package com.example.springboot_day01.system.config;

import com.example.springboot_day01.system.handle.LoginHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author fly
 */
@Configuration
public class HandleConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginHandle loginHandle;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandle);
        super.addInterceptors(registry);
    }
}

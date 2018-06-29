package com.example.springboot_day01.system.config;

import com.example.springboot_day01.system.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fly
 * 统一异常处理配置
 */

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(CustomException.class)
    public Map jsonException(CustomException c){
        Map map = new HashMap();
        map.put("code",c.getCode());
        map.put("message",c.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView pageException(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("exception");
        return mav;
    }

}

package com.example.springboot_day01.view;

import com.example.springboot_day01.system.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author fly
 */
@Controller
@RequestMapping("/w")
public class IndexController {

    @GetMapping("/index")
    public String index() throws Exception {
        throw new CustomException(501,"系统出现异常");
    }

    @GetMapping("/error")
    public String error() throws Exception {
        throw new Exception();
    }


}

package com.example.springboot_day01.view;


import com.example.springboot_day01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping("/set")
    public Object set(){
        return studentService.queryStudent("刘帅");
    }

    @GetMapping("/get")
    public Object get(){
        return studentService.querySystemRole();
    }

}


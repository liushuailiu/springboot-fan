package com.example.springboot_day01.view;

import com.example.springboot_day01.system.exception.CustomException;
import com.example.springboot_day01.system.file.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author fly
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @GetMapping("/index")
    public String index() throws Exception {
        throw new CustomException(501,"系统出现异常");
    }

    @GetMapping("/error")
    public String error() throws Exception {
        throw new Exception();
    }

    @GetMapping("/entity")
    public Object restfulEntity(){
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://gturnquist-quoters.cfapps.io/api/random",String.class);
        responseEntity.getStatusCode();
        responseEntity.getHeaders();
        String string = responseEntity.getBody();
        return string;
    }

    @GetMapping("/object")
    public Object restfulObject(){
        String s =
                restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random",String.class);
        return s;
    }

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile){
        String path = fileSystemStorageService.store(multipartFile);
        return path;
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String filename){
        Resource resource = fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+filename+"\"")
                .body(resource);
    }


}

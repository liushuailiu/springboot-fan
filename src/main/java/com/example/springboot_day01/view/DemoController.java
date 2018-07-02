package com.example.springboot_day01.view;

import com.example.springboot_day01.service.StudentService;
import com.example.springboot_day01.system.exception.CustomException;
import com.example.springboot_day01.system.file.FileSystemStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * @author fly
 */
@RestController
@RequestMapping("/demo")
@Api(value = "DemoController",tags = {"功能演示控制器"})
public class DemoController {

    /**
     * 调用远程接口的api,
     * 此接口为spring内置,
     * 可以直接用来注入使用,
     * 不需要配置,我们将通过 restTemplate
     * 来调用第三方为我们提供的接口,
     * 比如 支付宝/微信支付/天气接口...
     */

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "访问项目首页",notes = "springboot不支持直接访问静态页面,所有需要通过控制器跳转")
    @GetMapping("/index")
    public String index() throws Exception {
        throw new CustomException(501,"系统出现异常");
    }

    @GetMapping("/error")
    public String error() throws Exception {
        throw new Exception();
    }


    /**
     * restTemplate 支持我们常用的请求类型,
     * 比如 delete()/put(),但是通常情况下,
     * 第三方接口是不会提供删除/添加功能的(除了我们自己系统内部间的调用),
     * 所以一般只使用 get/post 请求, 根据返回内容的不同,
     * rest 提供了以下几个方法 :
     *      1.  getForEntity / postForEntity
     *      2.  getForObject / postForObject
     *      entity类的方法包含一个完整的请求头信息,比如浏览器信息,状态码,信息正文...
     *      object类的方法只包含一个信息正文,在请求时可以提供一个信息正文的类型,
     *      rest 将自动装箱,如果需要传入参数,则需要提供一个map集合,将参数装入map
     *      进行传输(不建议直接写到url当中,比如 http://www.baidu.com?name=tom&pass=123 );
     * @return
     */

    @GetMapping("/entity")
    public Object restfulEntity(){
        HashMap hashMap = new HashMap();
        hashMap.put("name","tom");
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://gturnquist-quoters.cfapps.io/api/random",String.class,hashMap);
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

    /**
     * 文件上传的接口,与SSM没有区别,需要注意的是
     * 页面上的from表单必须提供 enctype="multipart/form-data" 属性
     * @param multipartFile 上传文件字节流对象
     * @return
     */

    @ApiOperation(value = "文件上传接口",notes = "用户需要提供一个name=file的字节流文件类型")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "file",value = "上传文件")}
    )
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile){
        String path = fileSystemStorageService.store(multipartFile);
        return path;
    }


    /**
     * 下载文件的接口,与SSM也没有区别,需要注意的是,
     * 这既是一个下载接口,也是一个展示图片的接口(只适用于图片与视频),
     * 如果你的请求地址为 : <a  ="/demo/download?filename=2018-06-30/dd9cca9215154be0a5ce4045f74a1f97.PNG">下载</a>,
     * 那么就会提供下载功能, 如果地址为 : <img src="/demo/download?filename=2018-06-30/dd9cca9215154be0a5ce4045f74a1f97.PNG">,
     * 他就会自动展示图片(实际中应用非常广泛,这样做可以避免暴露服务器真实地址),
     * 当然使用传统的通过虚拟路径的方式也可以访问到服务器图片,
     * 但是因为springboot内置tomact,所以我们需要在配置文件中配置虚拟路径,
     * 并且在拦截器中提供相应配置 见 : HandleConfig
     * @param filename
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(String filename){
        Resource resource = fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+filename+"\"")
                .body(resource);
    }

    /**
     * 通过用户登录,触发事件
     * @return
     */
    @GetMapping("/login")
    public Object studentLogin(){
        return studentService.queryStudentById(1);
    }


}

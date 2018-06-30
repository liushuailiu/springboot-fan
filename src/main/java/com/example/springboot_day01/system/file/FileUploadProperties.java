package com.example.springboot_day01.system.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author fly
 */
@ConfigurationProperties
@Component
@Data
public class FileUploadProperties {

    private String location = "uploadDir";
    private String staticLocation = "E:/image/";

}

package com.example.springboot_day01.pojo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */

@ToString
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double id;
    private String username;
    private String password;

}

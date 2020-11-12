package com.twy.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gongpeng
 * @date 2020/11/12 22:42
 */
@SpringBootApplication
@MapperScan("com.twy.eduservice.mapper")
public class EduServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class, args);
    }
}

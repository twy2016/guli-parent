package com.twy.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gongpeng
 * @date 2020/12/18 22:53
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.twy")
@MapperScan("com.twy.ucenter.mapper")
@EnableCaching
public class ServiceUcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}

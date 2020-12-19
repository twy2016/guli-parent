package com.twy.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gongpeng
 * @date 2020/12/18 22:53
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.twy")
@MapperScan("com.twy.cms.mapper")
@EnableCaching
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}

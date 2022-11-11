package com.example.admitad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.admitad.repository", "com.example.admitad.myBatisPlus"})
@EnableRetry
@EnableWebMvc
public class AdmitadApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmitadApplication.class, args);
    }
}
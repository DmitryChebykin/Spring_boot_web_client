package com.example.admitad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.admitad.repository")
public class AdmitadApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmitadApplication.class, args);
    }
}
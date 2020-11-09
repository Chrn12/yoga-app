package com.woniu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woniu")
public class CoachStart {
    public static void main(String[] args) {
        SpringApplication.run(CoachStart.class,args);
    }
}

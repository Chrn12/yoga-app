package com.woniu;

import com.woniu.yoga.domain.TCoach;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woniu.mapper")
public class VenueStart {
    public static void main(String[] args) {
        SpringApplication.run(VenueStart.class,args);
    }
}

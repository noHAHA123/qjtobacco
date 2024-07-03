package com.example.sqlwork2;

import com.example.sqlwork2.service.TTSERVICE;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class tt {


    public static void main(String[] args) {
        SpringApplication.run(Sqlwork2Application.class, args);
    }

}

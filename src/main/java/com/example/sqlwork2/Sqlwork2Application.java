package com.example.sqlwork2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sqlwork2.mapper")
public class Sqlwork2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sqlwork2Application.class, args);
    }

}

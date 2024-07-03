package com.example.sqlwork2.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("farmer")
public class Farmer {
    private int id;

    private String name;
    private String intoCard;
    private String rollCard;
}
